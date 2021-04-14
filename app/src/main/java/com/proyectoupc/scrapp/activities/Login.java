package com.proyectoupc.scrapp.activities;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyectoupc.scrapp.R;
import com.proyectoupc.scrapp.adapters.SliderBgAdapter;
import com.proyectoupc.scrapp.helpers.ConnectionSQLiteHelper;
import com.proyectoupc.scrapp.models.SliderBg;
import com.proyectoupc.scrapp.models.User;
import com.proyectoupc.scrapp.utils.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Login extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    private RelativeLayout rl_container;
    private LinearLayout ll_login, ll_register;
    private EditText et_email, et_password, et_rUsername, et_rEmail, et_rPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private ProgressDialog progressDialog;
    private ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full Screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            final WindowInsetsController insetsController = getWindow().getDecorView().getWindowInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars());
            }
        } else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
        setContentView(R.layout.activity_login);

        rl_container = findViewById(R.id.login_container);
        viewPager2 = findViewById(R.id.viewPagerBg);
        ll_login = findViewById(R.id.ll_login);
        ll_register = findViewById(R.id.ll_register);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_rUsername = findViewById(R.id.et_rUser);
        et_rEmail = findViewById(R.id.et_rEmail);
        et_rPassword = findViewById(R.id.et_rPassword);
        TextView txt_newUser = findViewById(R.id.txt_register);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btn_register);
        Button btn_back = findViewById(R.id.btn_back);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        conn = new ConnectionSQLiteHelper(this, Common.DB_NAME, null, 1);

        loadBackground();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        txt_newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterForm();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginForm();
            }
        });
    }

    private void loadBackground() {
        final List<SliderBg> sliderBgs = new ArrayList<>();
        sliderBgs.add(new SliderBg(R.drawable.w3));
        sliderBgs.add(new SliderBg(R.drawable.w2));
        sliderBgs.add(new SliderBg(R.drawable.w1));
        sliderBgs.add(new SliderBg(R.drawable.w4));
        sliderBgs.add(new SliderBg(R.drawable.w5));

        viewPager2.setAdapter(new SliderBgAdapter(sliderBgs, viewPager2));

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000);
            }
        });

    }

    //Auto Slider
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    private void loginUser() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (email.isEmpty()) {
            et_email.setError("El correo electónico es obligatorio");
            et_email.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Correo electrónico inválido");
            et_email.requestFocus();
        }

        if (password.isEmpty()) {
            et_password.setError("La contraseña es obligatoria");
            et_password.requestFocus();
        }

        if (password.length() < 6) {
            et_password.setError("La contraseña debe tener al menos 6 caracteres");
            et_password.requestFocus();
        }

        progressDialog = ProgressDialog.show(Login.this,
                "Iniciando sesión",
                "Espere por favor",
                true,
                false);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        } else {
                            Snackbar.make(rl_container, Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showRegisterForm() {
        ll_login.setVisibility(View.GONE);
        ll_register.setVisibility(View.VISIBLE);
    }

    private void showLoginForm() {
        ll_login.setVisibility(View.VISIBLE);
        ll_register.setVisibility(View.GONE);
    }

    private void registerUser() {
        final String username = et_rUsername.getText().toString().trim();
        final String email = et_rEmail.getText().toString().trim();
        String password = et_rPassword.getText().toString().trim();

        if (username.isEmpty()) {
            et_rUsername.setError("El nombre es obligatorio");
            et_rUsername.requestFocus();
        }

        if (email.isEmpty()) {
            et_rEmail.setError("El correo electrónico es obligatorio");
            et_rEmail.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_rEmail.setError("Ingrese un correo válido");
            et_rEmail.requestFocus();
        }

        if (password.isEmpty()) {
            et_rPassword.setError("La contraseña es obligatoria");
            et_rPassword.requestFocus();
        }

        if (password.length() < 6) {
            et_rPassword.setError("La contraseña debe tener al menos 6 caracteres");
            et_rPassword.requestFocus();
        }

        progressDialog = ProgressDialog.show(Login.this,
                "Registrando usuario",
                "Espere por favor",
                true,
                false);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //create user on SQLite database
                            String userId = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid();
                            createUserInDatabase(userId, username, email);
                        } else {
                            progressDialog.dismiss();
                            Snackbar.make(rl_container, "Ocurrió un error, intente de nuevo.", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void createUserInDatabase(String id, String username, String email) {
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Common.U_ID, id);
        value.put(Common.U_NAME, username);
        value.put(Common.U_EMAIL, email);

        //Get id result to register
        long idResult = db.insert(Common.USER_TABLE, Common.U_ID, value);
        //Toast.makeText(this, "Registro ID:" + idResult, Toast.LENGTH_SHORT).show();
        db.close();

        //close dialog
        progressDialog.dismiss();

        clearEditText(et_rUsername);
        clearEditText(et_rEmail);
        clearEditText(et_rPassword);

        //show message
        Snackbar.make(rl_container, "Usuario registrado con éxito!", Snackbar.LENGTH_SHORT).show();

    }

    private void clearEditText(EditText editText) {
        editText.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Comentar para que entre en automatico desde el registro  anterior
        //if (mUser != null) {
        //  startActivity(new Intent(Login.this, MainActivity.class));
        //  finish();
        //}
    }

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
    }

}