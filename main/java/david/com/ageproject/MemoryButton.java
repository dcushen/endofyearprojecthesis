package david.com.ageproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by David on 24/04/2018.
 */

public class MemoryButton extends Button {

    protected int row;
    protected int column;
    protected int frontDrawableId;
    protected boolean isFlipped = false;
    protected boolean isMatched = false;
    protected Drawable front;
    protected Drawable back;

    public MemoryButton(Context context, int r, int c, int frontImageDrawableId) {
        super(context);

        row = r;
        column = c;
        frontDrawableId = frontImageDrawableId;

        front = context.getDrawable(frontImageDrawableId);
        back = context.getDrawable(R.drawable.question);
        setBackground(back);

        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
        tempParams.width = (int) getResources().getDisplayMetrics().density * 50;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 50;
        setLayoutParams(tempParams);

    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public int getFrontDrawableId() {
        return frontDrawableId;
    }

    public void flip() {
        if(isMatched)
            return;
        if(isFlipped)
        {
            setBackground(back);
            isFlipped = false;
        }
        else
        {
            setBackground(front);
            isFlipped = true;
        }
    }
}
