package com.example.fontextractor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import com.angads25.filepicker.view.FilePickerDialog;
import com.angads25.filepicker.controller.DialogSelectionListener;
import com.angads25.filepicker.model.DialogConfigs;
import com.angads25.filepicker.model.DialogProperties;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView filePathText;
    private TextView outputTextView;
    private String selectedFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filePathText = findViewById(R.id.filePathText);
        outputTextView = findViewById(R.id.outputTextView);
        Button selectFileButton = findViewById(R.id.selectFileButton);
        Button extractFontsButton = findViewById(R.id.extractFontsButton);

        selectFileButton.setOnClickListener(view -> showFilePicker());

        extractFontsButton.setOnClickListener(view -> {
            if (selectedFilePath != null) {
                FontExtractor extractor = new FontExtractor(selectedFilePath, outputTextView);
                extractor.extractFonts();
            } else {
                Toast.makeText(this, "Please select a file first!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showFilePicker() {
        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.extensions = new String[]{"xlsx"};

        FilePickerDialog dialog = new FilePickerDialog(this, properties);
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                if (files.length > 0) {
                    selectedFilePath = files[0];
                    filePathText.setText(selectedFilePath);
                }
            }
        });
        dialog.show();
    }
}
