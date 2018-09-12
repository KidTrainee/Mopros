package jp.co.ienter.mopros.models.base;

public abstract class Selectable {
    protected boolean isSelected;

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
