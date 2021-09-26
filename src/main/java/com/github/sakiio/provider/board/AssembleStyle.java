package com.github.sakiio.provider.board;

public enum AssembleStyle
{
    KOHI("KOHI", 0, true, 15), 
    VIPER("VIPER", 1, true, -1), 
    MODERN("MODERN", 2, false, 1);
    
    private boolean descending;
    private int startNumber;
    
    private AssembleStyle(final String s, final int n, final boolean descending, final int startNumber) {
        this.setDescending(descending);
        this.setStartNumber(startNumber);
    }
    
    public int getStartNumber() {
        return this.startNumber;
    }
    
    public void setStartNumber(final int startNumber) {
        this.startNumber = startNumber;
    }
    
    public boolean isDescending() {
        return this.descending;
    }
    
    public void setDescending(final boolean descending) {
        this.descending = descending;
    }
}
