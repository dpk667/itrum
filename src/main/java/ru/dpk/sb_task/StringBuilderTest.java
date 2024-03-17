package ru.dpk.sb_task;

import java.util.ArrayList;

public class StringBuilderTest {
    private StringBuilder str;
    private ArrayList<StringBuilder> snapshot;
    private int snapshotCount;

    public StringBuilderTest(){
        str = new StringBuilder();
        snapshot = new ArrayList<StringBuilder>();
        snapshotCount = 0;

    }

    public void append(String string){
        str.append(string);
    }

    public void delete(int  start, int finish){
        str.delete(start, finish);
    }

    public String toString() {
        return str.toString();
    }


    public void undo() {
        if (snapshotCount > 0) {
            snapshotCount--;
            str.setLength(0);
            str.append(snapshot.get(snapshotCount)); // Восстанавливаем предыдущий снимок
        }
    }

    public void saveSnapshot() {
        snapshotCount++;
        if (snapshotCount < snapshot.size()) {
            snapshot.subList(snapshotCount, snapshot.size());
        }
        snapshot.add(new StringBuilder(str));
    }

    public static void main(String[] args) {
        StringBuilderTest sb = new StringBuilderTest();
        sb.append("Hello ");
        sb.saveSnapshot();
        System.out.println(sb);
        sb.append("world! ");
        System.out.println(sb);

        sb.undo();
        System.out.println(sb);
    }
}
