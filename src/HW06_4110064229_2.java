public class HW06_4110064229_2 extends MedianOfArrays {
    /**
     * DoubleArrayIterator
     */
    public class Array2DIterator {
        private int n = 0;
        public int[][] a;
        public int x, y;
        public int index;
        public Array2DIterator(int[][] _a) {
            this.a = _a;
            for (int i = 0; i < a.length; i++) {
                n += a[i].length;
            }
        }
        public Array2DIterator(Array2DIterator copy) {
            this.a = copy.a;
            this.x = copy.x;
            this.y = copy.y;
            this.n = copy.n;
            this.index = copy.index;
        }
        public void setStart() {
            this.x = 0;
            this.y = 0;
            this.index = 0;
        }
        public void setEnd() {
            this.x = a.length - 1;
            this.y = a[x].length;
            this.index = n;
        }
        public int get() {
            return a[x][y];
        }
        public void set(int value) {
            this.a[x][y] = value;
        }
        public void increase() {
            if (y == a[x].length - 1) {
                x++;
                if (x == a.length) {
                    System.out.println("You increase too many la!");
                }
                y = 0;
                index++;
                return;
            }
            y++;
            index++;
            return;
        }
        public void decrease() {
            if (y == 0) {
                x--;
                if (x == -1) {
                    System.out.println("You decrease too many la!");
                }
                y = a[x].length - 1;
                index--;
                return;
            }
            y--;
            index--;
            return;
        }
        public Boolean lessBy1(Array2DIterator b) {
            if (b.x - this.x == 1) {
                return this.y == a[x].length && b.y == 0;
            }
            return this.x == b.x && b.y - this.y == 1;
        }
        public Boolean lessBy2(Array2DIterator b) {
            Array2DIterator tmp = new Array2DIterator(b);
            tmp.decrease();
            tmp.decrease();
            return this.x == b.x && this.y == b.y;
        }
    }
    @Override
    public double find_median(int[][] arrays) {
        Array2DIterator start = new Array2DIterator(arrays);
        Array2DIterator end = new Array2DIterator(arrays);
        start.setStart();
        end.setEnd();
        
        int n = start.n;
        if (n % 2 == 0)
            return find_median_even(arrays, n / 2 - 1, n / 2, start, end);
        return find_median_odd(arrays, n / 2, start, end);
    }

    public double find_median_odd(int[][] a, int target, Array2DIterator start, Array2DIterator end) {
        if (start.lessBy1(end)) {
            if (target != start.index)
                System.out.println("ERROR!! Your algorithm must have a bug in somewhere. target should be equal to start!");
            return start.get();
        }
        int randomX = (int) (Math.random()*(end.x - start.x)) + start.x;
        int randomY;
        if (start.x == end.x)
            randomY = (int) (Math.random()*(end.y - start.y)) + start.y;
        else if (randomX == start.x)
            randomY = (int) (Math.random()*(a[start.x].length - start.y)) + start.y;
        else
            randomY = (int) (Math.random()*(a[randomX].length));
        int pivot = a[randomX][randomY];
        Array2DIterator end_minus1 = new Array2DIterator(end);
        end_minus1.decrease();
        a[randomX][randomY] = end_minus1.get();
        end_minus1.set(pivot);
        Array2DIterator itr = new Array2DIterator(start), jtr = new Array2DIterator(end_minus1);
        jtr.decrease();
        while (true) {
            while (itr.get() < pivot) { // 都不要有等號比較好
                itr.increase();
            }
            while ((jtr.x != start.x || jtr.y != start.y) && jtr.get() > pivot) { // 都不要有等號比較好
                jtr.decrease();;
            }
            if (itr.x > jtr.x || itr.x == jtr.x && itr.y >= jtr.y)
                break;
            int tmp = itr.get();
            itr.set(jtr.get());
            jtr.set(tmp);
            itr.increase();
            jtr.decrease();
        }
        end_minus1.set(itr.get());
        itr.set(pivot);
        if (itr.index > target)
            return find_median_odd(a, target, start, itr);
        else if (itr.index < target) {
            itr.increase();
            return find_median_odd(a, target, itr, end);
        }
        else {
            return pivot;
        }
    }

    public double find_median_even(int[][] a, int target1, int target2, Array2DIterator start, Array2DIterator end) {
        if (start.lessBy2(end)) {
            end.decrease();
            if (target1 != start.index || target2 != end.index)
                System.out.println("ERROR!! Your algorithm must have a bug in somewhere. target1 should be equal to start and target2 should be equal to end!");
            return (start.get() + end.get()) / 2;
        }
        int randomX = (int) (Math.random()*(end.x - start.x)) + start.x;
        int randomY;
        if (randomX == start.x)
            randomY = (int) (Math.random()*(a[start.x].length - start.y)) + start.y;
        else
            randomY = (int) (Math.random()*(a[randomX].length));
        int pivot = a[randomX][randomY];
        Array2DIterator end_minus1 = new Array2DIterator(end);
        end_minus1.decrease();
        a[randomX][randomY] = end_minus1.get();
        end_minus1.set(pivot);
        Array2DIterator itr = new Array2DIterator(start), jtr = new Array2DIterator(end_minus1);
        jtr.decrease();
        while (true) {
            while (itr.get() < pivot) { // 都不要有等號比較好
                itr.increase();;
            }
            while ((jtr.x != start.x || jtr.y != start.y) && jtr.get() > pivot) { // 都不要有等號比較好
                jtr.decrease();;
            }
            if (itr.x > jtr.x || itr.x == jtr.x && itr.y >= jtr.y)
                break;
            int tmp = itr.get();
            itr.set(jtr.get());
            jtr.set(tmp);
        }
        end_minus1.set(itr.get());
        itr.set(pivot);
        if (itr.index > target2)
            return find_median_even(a, target1, target2, start, itr);
        else  if (itr.index < target1) {
            itr.increase();
            return find_median_even(a, target1, target2, itr, end);
        }
        else if (itr.index == target2) {
            int max = start.get();
            for (Array2DIterator ptr = new Array2DIterator(start); ptr.index < target2; ptr.increase()) {
                max = Math.max(max, ptr.get());
            }
            return (double)(pivot + max) / 2;
        }
        else {
            int min = end_minus1.get();
            for (Array2DIterator ptr = new Array2DIterator(end_minus1); ptr.index > target1; ptr.decrease()) {
                min = Math.min(min, ptr.get());
            }
            return (double)(pivot + min) / 2;
        }
    }
}
