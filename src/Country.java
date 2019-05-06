import java.util.Arrays;

public class Country {

    int count;
    int[] countUniqueId;
    String name;

    public String getName() {
        return name;
    }

    public int getCountUniqueId() {
        return countUniqueId.length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    void add(int count) {
        this.count += count;
    }

    void addCountUniqueId(int id) {
        if (countUniqueId == null) {

            countUniqueId = new int[1];
            countUniqueId[0] = id;
            return;
        }

        else {

            for (int i = 0; i < countUniqueId.length; i++) {

                if (countUniqueId[i] == id) {
                    return;
                }

            }
        }


        int[] c = Arrays.copyOf(countUniqueId, countUniqueId.length + 1);
        countUniqueId = c;
        countUniqueId[countUniqueId.length - 1] = id;

    }
}
