package com.example.nwravens.actions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nwravens.activities.home.category.CategoryScreenNotificationsAdapter;

public class SwipeActionsCallback extends ItemTouchHelper.SimpleCallback {

    private CategoryScreenNotificationsAdapter mAdapter;
    private Drawable deleteIcon;
    private Drawable editIcon;
    private ColorDrawable deleteBackground;
    private ColorDrawable editBackground;

    public SwipeActionsCallback(CategoryScreenNotificationsAdapter adapter, Drawable deleteIcon, Drawable editIcon, int deleteBackgroundColor, int editBackgroundColor) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
        this.deleteIcon = deleteIcon;
        this.editIcon = editIcon;
        deleteBackground = new ColorDrawable(deleteBackgroundColor);
        editBackground = new ColorDrawable(editBackgroundColor);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // Not used, so we return false
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT) {
            mAdapter.deleteItem(position);
        } else if (direction == ItemTouchHelper.RIGHT) {
            mAdapter.markAsRead(position);
        }
    }

    @Override
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();

        boolean isCancelled = dX == 0 && !isCurrentlyActive;

        if (isCancelled) {
            clearCanvas(canvas, itemView.getRight() + dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }

        if (dX < 0) {
            // Swiping to the left this refers to (delete action)
            deleteBackground.setColor(Color.RED);
            deleteBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
            deleteBackground.draw(canvas);
            int deleteIconMargin = (itemHeight - deleteIcon.getIntrinsicHeight()) / 2;
            int deleteIconTop = itemView.getTop() + (itemHeight - deleteIcon.getIntrinsicHeight()) / 2;
            int deleteIconBottom = deleteIconTop + deleteIcon.getIntrinsicHeight();
            int deleteIconLeft = itemView.getRight() - deleteIconMargin - deleteIcon.getIntrinsicWidth();
            int deleteIconRight = itemView.getRight() - deleteIconMargin;
            deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
            deleteIcon.draw(canvas);
        } else if (dX > 0) {
            // Swiping to the right (edit action)
            editBackground.setColor(Color.BLUE);
            editBackground.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + (int) dX, itemView.getBottom());
            editBackground.draw(canvas);

            int editIconMargin = (itemHeight - editIcon.getIntrinsicHeight()) / 2;
            int editIconTop = itemView.getTop() + (itemHeight - editIcon.getIntrinsicHeight()) / 2;
            int editIconBottom = editIconTop + editIcon.getIntrinsicHeight();
            int editIconLeft = itemView.getLeft() + editIconMargin;
            int editIconRight = itemView.getLeft() + editIconMargin + editIcon.getIntrinsicWidth();
            editIcon.setBounds(editIconLeft, editIconTop, editIconRight, editIconBottom);
            editIcon.draw(canvas);
        }


    }
    private void clearCanvas(Canvas canvas, float left, float top, float right, float bottom) {
        canvas.drawRect(left, top, right, bottom, new Paint(Paint.ANTI_ALIAS_FLAG));
    }
}
