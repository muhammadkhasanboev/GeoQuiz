package android.bignerdranch.com;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;

    private int currentQuestionIndex = 0;
    private int score = 0;

    // Array of questions
    Question[] questions = new Question[]{
            new Question("OOP stands for Object Oriented Programming", true),
            new Question("Java is functional programming language", false),
            new Question("Java runs on JVM", true),
            new Question("int type is 8 bytes", false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set system bar insets (edge-to-edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mQuestionTextView = findViewById(R.id.question_text_view); // Make sure this exists in XML

        // Show the first question
        showNextQuestion();

        // True button logic
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        // False button logic
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    // Method to show current question or finish quiz
    private void showNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            mQuestionTextView.setText(questions[currentQuestionIndex].getText());
        } else {
            mQuestionTextView.setText("Quiz finished! Score: " + score + "/" + questions.length);
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }
    }

    // Method to check if answer is correct
    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = questions[currentQuestionIndex].isAnswerTrue();

        if (userAnswer == correctAnswer) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        showNextQuestion();
    }
}
