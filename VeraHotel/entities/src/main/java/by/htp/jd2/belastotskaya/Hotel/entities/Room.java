package by.htp.jd2.belastotskaya.Hotel.entities;

/**
 * Created by diby on 23.10.2016.
 */
public class Room extends Entity {
    private static final long serialVersionUID = 1L;

    private int idRoom;
    private int number;
    private String room_type;
    private int price_a_day;
    private int status;


    @Override
    public String toString() {
        return "Room [idRoom=" + idRoom + ", number=" + number + ", room_type=" + room_type + ", price_a_day" + price_a_day +  "]";
    }

    /**
     * @return the idRoom
     */
    public int getIdRoom() {
        return idRoom;
    }

    /**
     * @param idRoom the firstName to set
     */
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param  number  to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the room_type
     */
    public String getRoom_type() {
        return room_type;
    }

    /**
     * @param room_type  to set
     */
    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    /**
     * @return the price_a_day
     */
    public int getPrice_a_day() {
        return price_a_day;
    }

    /**
     * @param price_a_day  to set
     */
    public void setPrice_a_day(int price_a_day) {
        this.price_a_day = price_a_day;
    }

    /**
     * @return the accountId
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the accountId to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the accessLevel
     */

}



