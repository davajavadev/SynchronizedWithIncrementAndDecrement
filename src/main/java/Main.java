public class Main {
    private static int i = 0;

    public static void main(String[] args) {
        Thread incrementThread = new Thread(new IncrementTask());
        Thread decrementThread = new Thread(new DecrementTask());

        incrementThread.start();
        decrementThread.start();
    }

    static class IncrementTask implements Runnable {
        @Override
        public void run() {
            synchronized (Main.class) {
                i += 10;
                System.out.println("Incremented i to: " + i);
            }
        }
    }

    static class DecrementTask implements Runnable {
        @Override
        public void run() {
            try {
                // Подождем, пока первый поток увеличит число
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (Main.class) {
                i -= 5;
                System.out.println("Decremented i to: " + i);
            }
        }
    }

}
