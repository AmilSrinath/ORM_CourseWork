package lk.ijse.orm_coursework.bo;

import lk.ijse.orm_coursework.bo.Custom.Impl.RoomBOImpl;
import lk.ijse.orm_coursework.bo.Custom.Impl.StudentBOImpl;
import lk.ijse.orm_coursework.bo.Custom.Impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,ROOM,STUDENT
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case USER:
                return new UserBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}
