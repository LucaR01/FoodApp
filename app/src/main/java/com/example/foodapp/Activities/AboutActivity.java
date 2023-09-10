package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodapp.R;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView privacyPolicy;
    private Button sendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();

        openPrivacyPolicy();

        handleSendFeedback();
    }

    private void initView() {
        this.privacyPolicy = findViewById(R.id.aboutPrivacyPolicyTextView);
        this.sendFeedback = findViewById(R.id.aboutFeedbackButton);
    }

    private void openPrivacyPolicy() {
        this.privacyPolicy.setOnClickListener(view -> {
            final Intent privacyPolicyIntent = new Intent(AboutActivity.this, PrivacyPolicyActivity.class);
            startActivity(privacyPolicyIntent);
        });
    }

    private void handleSendFeedback() {
        this.sendFeedback.setOnClickListener(view -> {
            final Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:contact@example.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Food App");
            startActivity(emailIntent);
        });
    }
}
