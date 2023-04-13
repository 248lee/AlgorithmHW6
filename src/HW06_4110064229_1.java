public class HW06_4110064229_1 extends MedianOfArrays {
    @Override
    public double find_median(int[][] arrays) {
        int n = 0;
        for (int i = 0; i < arrays.length; i++) {
            n += arrays[i].length;
        }
        int[] a = new int[n];
        int ptr = 0;
        for (int i = 0; i < arrays.length; i++) {
            for (int j : arrays[i]) {
                a[ptr++] = j;
            }
        }
        if (n % 2 == 0)
            return find_median_even(a, n / 2 - 1, n / 2, 0, n);
        return find_median_odd(a, n / 2, 0, n);
    }

    public double find_median_odd(int[] a, int target, int start, int end) {
        if (end - start == 1) {
            if (target != start)
                System.out.println("ERROR!! Your algorithm must have a bug in somewhere. target should be equal to start!");
            return a[target];
        }
        int random = (int) (Math.random()*(end - start)) + start;
        int pivot = a[random];
        a[random] = a[end - 1];
        a[end - 1] = pivot;
        int itr = start - 1, jtr = end - 1;
        while (true) {
            while (a[++itr] < pivot) { // 都不要有等號比較好
            }
            while (jtr != start && a[--jtr] > pivot) { // 都不要有等號比較好
            }
            if (itr >= jtr)
                break;
            int tmp = a[itr];
            a[itr] = a[jtr];
            a[jtr] = tmp;
        }
        a[end - 1] = a[itr];
        a[itr] = pivot;
        if (itr > target)
            return find_median_odd(a, target, start, itr);
        else if (itr < target)
            return find_median_odd(a, target, itr + 1, end);
        else {
            return pivot;
        }
    }

    public double find_median_even(int[] a, int target1, int target2, int start, int end) {
        if (end - start == 2) {
            if (target1 != start && target2 != end - 1)
                System.out.println("ERROR!! Your algorithm must have a bug in somewhere. target should be equal to start!");
            return (double)(a[target1] + a[target2]) / 2;
        }
        int random = (int) (Math.random()*(end - start)) + start;
        int pivot = a[random];
        a[random] = a[end - 1];
        a[end - 1] = pivot;

        int itr = start - 1, jtr = end - 1;
        while (true) {
            while (a[++itr] < pivot) { // 都不要有等號比較好
            }
            while (jtr != start && a[--jtr] > pivot) { // 都不要有等號比較好
            }
            if (itr >= jtr)
                break;
            int tmp = a[itr];
            a[itr] = a[jtr];
            a[jtr] = tmp;
        }
        a[end - 1] = a[itr];
        a[itr] = pivot;
        if (itr > target2)
            return find_median_even(a, target1, target2, start, itr);
        else  if (itr < target1)
            return find_median_even(a, target1, target2, itr + 1, end);
        else if (itr == target2) {
            int max = a[start];
            for (int i = start; i < target2; i++) {
                max = Math.max(max, a[i]);
            }
            return (double)(pivot + max) / 2;
        }
        else {
            int min = a[end - 1];
            for (int i = end - 1; i > target1; i--) {
                min = Math.min(min, a[i]);
            }
            return (double)(pivot + min) / 2;
        }
    }
}
