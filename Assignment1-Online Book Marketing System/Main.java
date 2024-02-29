import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.SimpleDateFormat;


//Fatih Öter
//2210356119
//BBM-104-Assignment-1


public class Main {
    public static void main(String[] args) throws IOException, ParseException {


        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        String purchaseOrderFile = args[0];
        String priceCatalogFile = args[1];


        //String fileName = "priceCatalog1.txt";


        ArrayList<ArrayList<String>> priceList = new ArrayList<>();
        List<PriceCatalogEntry> priceCatalog = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(priceCatalogFile))) {
            String row;

            while ((row = br.readLine()) != null) {
                String[] rowParts = row.split("\t");
                if (rowParts.length == 5) {


                    ArrayList<String> rowList = new ArrayList<>();
                    Collections.addAll(rowList, rowParts);

                    priceList.add(rowList);
                }
            }
        } catch (IOException e) {
            writer.write("the txt file has not found"+"\n");

        }

        ArrayList<Books> books = new ArrayList<>();

        for(ArrayList<String> rowList : priceList){
            for (String rowParts:rowList) {

                Books newBook = new Books();
                newBook.setBookName(rowList.get(0));
                newBook.setMembershipType(rowList.get(1));
                String dateString = rowList.get(2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date dateValue = dateFormat.parse(dateString);
                newBook.setStartDate(dateValue);
                String dateString2 = rowList.get(3);
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
                Date dateValue2 = dateFormat2.parse(dateString2);
                newBook.setEndDate(dateValue2);
                newBook.setPrice(Double.valueOf(rowList.get(4)));
                books.add(newBook);
            }

        }



            //fileName = "purchaseOrder.
        //";

        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(purchaseOrderFile))) {
                String row;
                while ((row = br.readLine()) != null) {
                    String[] rowParts = row.split("\t");

                    if (rowParts.length >= 7) {
                        PurchaseOrder purchase = new PurchaseOrder();
                        purchase.setCustomerName(rowParts[0]);
                        purchase.setMembershipType(rowParts[1]);
                        purchase.setOrderDate(new SimpleDateFormat("dd.MM.yyyy").parse(rowParts[2]));
                        String[] parameters = new String[rowParts.length - 3];
                        System.arraycopy(rowParts, 3, parameters, 0, rowParts.length - 3);
                        purchase.setParameters(parameters);
                        purchaseOrders.add(purchase);

                    }

                }
            }
        catch (IOException exception) {
            writer.write("the txt file has not found"+"\n");
            }

        for (int i = 0; i < purchaseOrders.size(); i++) {
            PurchaseOrder order = purchaseOrders.get(i);
            String customerName = order.getCustomerName();
            String membershipType = order.getMembershipType();
            Date orderDate = order.getOrderDate();
            List<String> itemsPurchased = new ArrayList<>();
            double totalCost = 0.0;

            for (int j = 0; j < order.getParameters().length; j += 2) {
                String bookName = order.getParameters()[j];
                int quantity = Integer.parseInt(order.getParameters()[j + 1]);

                Books relatedBook = findBookByNameAndMembershipTypeAndDate(books, bookName, membershipType, orderDate);
                if (relatedBook != null) {
                    double eachPrice = relatedBook.calculatePrice(orderDate, quantity,books);
                    double subtotal = eachPrice * quantity;
                    totalCost += subtotal;

                    Date startDateStrInput = relatedBook.getStartDate();
                    Date endDateStrInput = relatedBook.getEndDate();
                    String startDateStrInputStr = String.valueOf(startDateStrInput);
                    String endDateStrInputStr = String.valueOf(endDateStrInput);
                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date startDate = inputDateFormat.parse(startDateStrInputStr);
                    Date endDate = inputDateFormat.parse(endDateStrInputStr);
                    String startDateStr = outputDateFormat.format(startDate);
                    String endDateStr = outputDateFormat.format(endDate);



                    String item = bookName + " (Qty: " + quantity + ") - " + eachPrice + " each\n" +
                            "(Valid from " + startDateStr + " to " + endDateStr + ")\n" +
                            "Subtotal: " + subtotal;
                    itemsPurchased.add(item);
                }
            }
            writer.write("------------Bill for Customer " + (i + 1) + "-------------"+"\n");
            writer.write("Customer: " + customerName+"\n");
            writer.write("Membership Type: " + membershipType+"\n");
            writer.write("Date: " + new SimpleDateFormat("dd.MM.yyyy").format(orderDate)+"\n");
            writer.write("\nItems Purchased:"+"\n");
            for (String item : itemsPurchased) {
                writer.write(item+"\n");
            }
            writer.write("\nTotal Cost: " + totalCost + "\n"+"\n");
        }
        writer.write("\n");
        writer.close();

    }



    private static Books findBookByNameAndMembershipTypeAndDate(ArrayList<Books> books, String bookName, String membershipType, Date orderDate) {
        for (Books book : books) {
            if (book.getBookName().equals(bookName) &&
                    book.getMembershipType().equals(membershipType) &&
                    orderDate.after(book.getStartDate()) &&
                    orderDate.before(book.getEndDate())) {
                return book;
            }
        }
        return null;

    }


}

