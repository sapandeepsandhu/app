package com.example.fontextractor;

import android.widget.TextView;

public class FontExtractor {
    private String filePath;
    private TextView outputTextView;

    public FontExtractor(String filePath, TextView outputTextView) {
        this.filePath = filePath;
        this.outputTextView = outputTextView;
    }

    public void extractFonts() {
        try {
            String result = SharedStringProcessor.processSharedStrings(filePath);
            result += InlineStringProcessor.processInlineStrings(filePath);
            outputTextView.setText(result);
        } catch (Exception e) {
            outputTextView.setText("Error: " + e.getMessage());
        }
    }
}
