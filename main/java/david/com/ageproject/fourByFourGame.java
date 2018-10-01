package david.com.ageproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class fourByFourGame extends AppCompatActivity implements View.OnClickListener {

    private int numberOfElements;
    private MemoryButton[] buttons;
    private int[] buttonsGraphicLocations;
    private int[] buttonGraphics;
    private MemoryButton selectedButtonOne;
    private MemoryButton selectedButtonTwo;
    protected int countMatches = 0;
    TextView matches;

    private boolean isBusy = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_by_four_game);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid_layout_4x4);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfElements = numColumns * numRows;
        buttons = new MemoryButton[numberOfElements];
        buttonGraphics = new int[numberOfElements / 2];
        buttonGraphics[0] = R.drawable.button_1;
        buttonGraphics[1] = R.drawable.button_2;
        buttonGraphics[2] = R.drawable.button_3;
        buttonGraphics[3] = R.drawable.button_4;
        buttonGraphics[4] = R.drawable.button_5;
        buttonGraphics[5] = R.drawable.button_6;
        buttonGraphics[6] = R.drawable.button_7;
        buttonGraphics[7] = R.drawable.button_8;

        buttonsGraphicLocations = new int[numberOfElements];
        shuffleButtonGraphics();

        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numColumns; c++) {
                MemoryButton tempButton = new MemoryButton(this, r, c, buttonGraphics[buttonsGraphicLocations[r * numColumns + c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r * numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }
        }
    }

    protected void shuffleButtonGraphics()
    {
        Random rand = new Random();
        for(int i = 0; i<numberOfElements; i++)
        {
            buttonsGraphicLocations[i] = i % (numberOfElements / 2);
        }
        for(int i=0; i<numberOfElements; i++)
        {
            int temp = buttonsGraphicLocations[i];
            int swapLocation = rand.nextInt(16);
            buttonsGraphicLocations[i] = buttonsGraphicLocations[swapLocation];
            buttonsGraphicLocations[swapLocation] = temp;
        }
    }

    @Override
    public void onClick(View view) {
        if (isBusy)
            return;
        MemoryButton button = (MemoryButton) view;
        if (button.isMatched)
            return;
        if (selectedButtonOne == null) {
            selectedButtonOne = button;
            selectedButtonOne.flip();
            return;
        }
        if (selectedButtonOne.getId() == button.getId()) {
            return;
        }
        if (selectedButtonOne.getFrontDrawableId() == button.getFrontDrawableId()) {
            button.flip();
            button.setMatched(true);
            selectedButtonOne.setMatched(true);
            selectedButtonOne.setEnabled(false);
            button.setEnabled(false);
            selectedButtonOne = null;
            countMatches++;
        } else {
            selectedButtonTwo = button;
            selectedButtonTwo.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedButtonTwo.flip();
                    selectedButtonOne.flip();
                    selectedButtonOne = null;
                    selectedButtonTwo = null;
                    isBusy = false;
                }
            }, 500);

        }

        if(countMatches == 8){
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplication().getApplicationContext());
            builder.setMessage("You have won the game!");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog winnerAlert = builder.create();
            winnerAlert.show();
        }
    }
}
