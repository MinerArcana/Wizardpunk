package com.minerarcana.wizardpunk.magic.cryptomancy.numeramancy;

import com.minerarcana.wizardpunk.api.cryptomancy.MiniGameInstance;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGameStatus;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import javax.annotation.Nonnull;
import java.awt.event.KeyEvent;
import java.util.List;

public class NumeramancyMiniGameInstance extends MiniGameInstance {
    private MiniGameStatus status = MiniGameStatus.NOT_STARTED;
    private final int gridSize = 4;
    private int endValue = 64;
    private int[] tileValues;

    @Override
    public void setup() {
        this.tileValues = new int[gridSize * gridSize];
        addTile();
        addTile();
    }

    @Override
    public void setDifficultyLevel(int difficultyLevel) {
        for (int i = 0; i < difficultyLevel; i++) {
            endValue = endValue * 2;
        }
    }

    @Override
    public void start() {
        this.status = MiniGameStatus.PLAYING;
    }

    @Override
    public void tick() {
        if (this.status == MiniGameStatus.PLAYING) {
            if (!canMove()) {
                this.status = MiniGameStatus.LOST;
            }
        }
    }

    @Override
    @Nonnull
    public MiniGameStatus getStatus() {
        return status;
    }

    @Override
    public boolean keyPressed(int keyCode, int scan, int modifiers) {
        switch (keyCode) {
            case 87:
            case 265:
                up();
                return true;
            case 83:
            case 264:
                down();
                return true;
            case 65:
            case 263:
                left();
                return true;
            case 68:
            case 262:
                right();
                return true;
            default:
                return false;
        }
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public void left() {
        boolean needAddTile = false;
        for (int i = 0; i < gridSize; i++) {
            int[] line = getLine(i);
            int[] merged = mergeLine(moveLine(line));
            setLine(i, merged);
            if (!needAddTile && !compare(line, merged)) {
                needAddTile = true;
            }
        }

        if (needAddTile) {
            addTile();
        }
    }

    public void right() {
        tileValues = rotate(180);
        left();
        tileValues = rotate(180);
    }

    public void up() {
        tileValues = rotate(270);
        left();
        tileValues = rotate(90);
    }

    public void down() {
        tileValues = rotate(90);
        left();
        tileValues = rotate(270);
    }

    boolean canMove() {
        if (!isFull()) {
            return true;
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int value = valueAt(x, y);
                if ((x < 3 && value == valueAt(x + 1, y)) || ((y < 3) && value == valueAt(x, y + 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFull() {
        return availableSpace().size() == 0;
    }

    private void addTile() {
        IntList intSet = availableSpace();
        if (!intSet.isEmpty()) {
            int index = (int) (Math.random() * intSet.size()) % intSet.size();
            int tileValueIndex = intSet.getInt(index);
            tileValues[tileValueIndex] = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private IntList availableSpace() {
        IntList availableSpace = new IntArrayList();
        for (int x = 0; x < tileValues.length; x++) {
            if (tileValues[x] <= 0) {
                availableSpace.add(x);
            }
        }
        return availableSpace;
    }


    public int valueAt(int x, int y) {
        return tileValues[x + y * gridSize];
    }

    private int[] getLine(int index) {
        int[] result = new int[gridSize];
        for (int i = 0; i < gridSize; i++) {
            result[i] = valueAt(i, index);
        }
        return result;
    }

    private void setLine(int index, int[] newLine) {
        System.arraycopy(newLine, 0, tileValues, index * gridSize, gridSize);
    }

    private int[] rotate(int angle) {
        int[] newTiles = new int[gridSize * gridSize];
        int offsetX = 3, offsetY = 3;
        if (angle == 90) {
            offsetY = 0;
        } else if (angle == 270) {
            offsetX = 0;
        }

        double rad = Math.toRadians(angle);
        int cos = (int) Math.cos(rad);
        int sin = (int) Math.sin(rad);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int newX = (x * cos) - (y * sin) + offsetX;
                int newY = (x * sin) + (y * cos) + offsetY;
                newTiles[(newX) + (newY) * 4] = valueAt(x, y);
            }
        }
        return newTiles;
    }

    private boolean compare(int[] line1, int[] line2) {
        if (line1 == line2) {
            return true;
        } else if (line1.length != line2.length) {
            return false;
        }

        for (int i = 0; i < line1.length; i++) {
            if (line1[i] != line2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] moveLine(int[] oldLine) {
        IntList l = new IntArrayList();
        for (int i = 0; i < gridSize; i++) {
            if (oldLine[i] != 0) {
                l.add(oldLine[i]);
            }
        }
        if (l.size() == 0) {
            return oldLine;
        } else {
            int[] newLine = new int[gridSize];
            ensureSize(l, gridSize);
            for (int i = 0; i < gridSize; i++) {
                newLine[i] = l.removeInt(0);
            }
            return newLine;
        }
    }

    private int[] mergeLine(int[] oldLine) {
        IntList list = new IntArrayList();
        for (int i = 0; i < gridSize && oldLine[i] != 0; i++) {
            int num = oldLine[i];
            if (i < 3 && oldLine[i] == oldLine[i + 1]) {
                num *= 2;
                if (num == endValue) {
                    this.status = MiniGameStatus.WON;
                }
                i++;
            }
            list.add(num);
        }
        if (list.size() == 0) {
            return oldLine;
        } else {
            ensureSize(list, gridSize);
            return list.toArray(new int[gridSize]);
        }
    }

    private static void ensureSize(List<Integer> l, int size) {
        while (l.size() != size) {
            l.add(0);
        }
    }
}
