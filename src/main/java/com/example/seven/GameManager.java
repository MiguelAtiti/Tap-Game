package com.example.seven;

public class GameManager {



    static int life=1;
    static int str=1;
    static String name;

    static int lifeE;
    static int strE;
    static String nameE;


    static int exp;

    static int enemyMin=2;
    static int enemyMax=30;

  static int defeated;





    private static GameManager instance;


    // METODO PARA CAMBIAR LAS STAST
    //igual un singleton no es buena idea porque lo que quiero es sobreescrivir las stat
    //que es static


    public static void setEnemyPower(int enemyMaxval, int enemyMinval){


        enemyMax=enemyMax+enemyMaxval;
        enemyMin=enemyMin+enemyMinval;


    }





    public static void setExp(int expval){

        exp=expval;

    }
    public static void setName(String newName){

        name=newName;

        System.out.println("El nombre es "+newName);
    }

    public static void setNameE(String newName){

        nameE=newName;


    }

    public static void setStats(int strval, int lifeval ) {

        if (GameManager.instance==null){

            GameManager.instance = new GameManager();

        }

        life=lifeval+life;

        str=strval+str;






        System.out.println("la vida vale "+life);
        System.out.println("la fuerza vale "+str);



    }


    public static void setEnemyStats(int strvalE, int lifevalE, int defeatednew) {

        if (GameManager.instance==null){

            GameManager.instance = new GameManager();

        }

        lifeE=lifevalE+lifeE;

        strE=strvalE+strE;

        defeated=defeatednew+defeated;


        System.out.println("defensa enemigo"+lifeE);
        System.out.println("fuerza enemigo "+strE);



    }










    // METODO PARA OBTENER LAS STATS

    public static int getDefeated(){


        return defeated;



    }



    public static int getExp(){


        return exp;



    }





    public static int getEnemyPowerMax(){


        return enemyMax;



    }

    public static int getEnemyPowerMin(){


        return enemyMin;



    }


    public static  int getLifeE(){

        return lifeE;
    }

    public static  int getStrE(){

        return strE;
    }





    public static  int getLife(){

        return life;
    }

    public static  int getStr(){

        return str;
    }

    public static  String getName(){

        return name;
    }

    public static  String getNameE(){




        switch (defeated) {
            case 1:
                return "El Asesino de Dragones";

            case 2:
                return "El Verdugo";

            case 3:
                return "El Rey del Cementerio";


            case 4:
                return "El Gran Lobo Gris";


            case 5:
                return "El Gran Lobo Gris";

            case 6:
                return "LORD OF CINDER";

            default:
                 return "Solair";
        }





    }

    public static  int getScore(){

        return exp;
    }





























}
