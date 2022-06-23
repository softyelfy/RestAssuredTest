public class RestAssuredTest {
    public static void main(String[] args) {
        //RestAssuredMain rest = new RestAssuredMain();
        //rest.getToken();
        String bookingDetail = "{\n" +
                "    \"firstname\" : \"Dorian\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 35000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-04-30\",\n" +
                "        \"checkout\" : \"2022-05-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        String token = RestAssuredMain.getToken();
        System.out.println("Токен - " + token);
        String bookingId = RestAssuredMain.createBooking(bookingDetail);
        System.out.println("Номер заказа - " + bookingId);
        RestAssuredMain.deleteBooking(token, bookingId);
        RestAssuredMain.getBooking(404, bookingId);
    }
}
