import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
   static Country[] country;

    public void fileRead(String filename, String regex) throws FileNotFoundException {

        File file = new File(filename);
        String[] val;



        if (file.canRead()) {
            Scanner scanner = new Scanner(file);


            if (scanner.hasNext()) {
                scanner.nextLine(); //ignore the first line user_id;count;country;
            }

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();
                val = data.split(regex);

                if ((val.length != 3) || (val[1].isEmpty() || val[2].isEmpty())) {

                    continue;
                }
                else {
                      try {
                          Integer.parseInt(val[1].trim());
                          Integer.parseInt(val[0].trim());
                      }
                      catch (NumberFormatException numb) {
                          //ignore this exception
                          continue;
                      }

                      if (country == null) {

                          Country c = new Country();
                          c.setName(val[2].trim());
                          c.add(Integer.parseInt(val[1].trim()));
                          c.addCountUniqueId(Integer.parseInt(val[0].trim()));
                          country = new Country[10];
                          country[0] = c;
                      }
                      else {

                          for (int i = 0; i < country.length; i++) {

                              if (country[i] == null) {
                                  Country c = new Country();
                                  c.setName(val[2].trim());
                                  c.add(Integer.parseInt(val[1].trim()));
                                  country[i] = c;
                                  country[i].addCountUniqueId(Integer.parseInt(val[0].trim()));
                                  break;

                              }

                              if (country[i].getName().equalsIgnoreCase(val[2])) {
                                  country[i].add(Integer.parseInt(val[1].trim()));
                                  country[i].addCountUniqueId(Integer.parseInt(val[0].trim()));
                                  break;
                              }
                              else if (i + 1 == country.length)  {

                                  Country[] c = Arrays.copyOf(country, country.length + 1);
                                  country = c;
                                  c = null;
                                  Country countr = new Country();
                                  countr.setName(val[2].trim());
                                  countr.add(Integer.parseInt(val[1].trim()));
                                  country[country.length - 1] = countr;
                                  country[country.length - 1].addCountUniqueId(Integer.parseInt(val[0].trim()));
                                  break;

                              }

                          }
                      }
                }


            }


        }
   }

    public static void main(String[] args) throws FileNotFoundException {
       //country;sum(count) ount_uniq(user_id)
       Parser parser = new Parser();
       parser.fileRead("test.txt", ";");

        System.out.println("country;  sum(count)   (число уникальных user_id для country)");

       for (Country c : country) {
           if (c != null) {
               System.out.println(c.getName() + ",  " + c.getCount() + ", " + c.getCountUniqueId());
           }
       }
    }
}
