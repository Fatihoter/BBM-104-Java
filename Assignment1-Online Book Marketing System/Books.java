import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Books {

    private String bookName;
    private String membershipType;
    private Date startDate;
    private Date endDate;
    private Double price;


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);

        return "Book Name: " + bookName + "\nMembership Type: " + membershipType +
                "\nStart Date: " + startDateStr + "\nEnd Date: " + endDateStr +
                "\nPrice: " + price;
    }


    public double calculatePrice(Date orderDate, int quantity, ArrayList<Books> books) {
        for (Books entry :books ) {
            if (bookName.equals(entry.getBookName())
                    && membershipType.equals(entry.getMembershipType())
                    && orderDate.after(entry.getStartDate())
                    && orderDate.before(entry.getEndDate())) {
                return entry.getPrice();
            }
        }
        return 0.0;
    }
}




