package lk.ijse.orm_coursework.bo;

import lk.ijse.orm_coursework.bo.Custom.Impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
