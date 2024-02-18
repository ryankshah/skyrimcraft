package com.ryankshah.skyrimcraft.util;

public class LevelUpdate
{
    private String updateName;
    private int level;
    private int levelUpRenderTime;

    public LevelUpdate(String updateName, int level, int levelUpRenderTime) {
        this.updateName = updateName;
        this.level = level;
        this.levelUpRenderTime = levelUpRenderTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelUpRenderTime() {
        return levelUpRenderTime;
    }

    public void setLevelUpRenderTime(int levelUpRenderTime) {
        this.levelUpRenderTime = levelUpRenderTime;
    }
}