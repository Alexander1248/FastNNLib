package ru.alexander.nnlib.tools;

public abstract class ThreadSplitter {
    private Thread[] threads;

    public ThreadSplitter(int threadCount) {
        threads = new Thread[threadCount];
    }
    public void execute(int size) {
        boolean wait;
        for (int i = 0; i < size; i++) {
            do {
                wait = true;
                for (int j = 0; j < threads.length; j++)
                    if (threads[j] == null || !threads[j].isAlive()) {
                        int finalI = i;
                        threads[j] = new Thread(() -> run(finalI));
                        threads[j].start();
                        wait = false;
                        break;
                    }

            } while(wait);
        }
        for (int i = 0; i < threads.length; i++) while (threads[i] != null &&threads[i].isAlive()) {}
    }

    public abstract void run(int gid);
}
