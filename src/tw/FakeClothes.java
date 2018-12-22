import java.util.*;

public class FakeClothes {

    public static List<String> strList = new ArrayList<String>();
    public static void main(String[] args) {
        /*LinkedList<Integer> list = new LinkedList<Integer>();

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        ListIterator<Integer> li = list.listIterator();
        while(li.hasNext()){
            if(li.next() == 20){
                li.remove();
            }
        }
        System.out.println(list);
        System.out.println("hello world");*/

        LinkedList<String> list = new LinkedList<String>();

        Map<String, String> mapData = new HashMap<String, String>();



        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};


        String str = "";

//        for (int i = 0; i < clothes.length; i++) {
//            for (int j = 0; j < clothes[i].length; j++) {
//                //list.addLast(clothes[i][j]);
//                if (i < clothes.length - 1 && j < clothes[i].length - 1) {
//                    if (clothes[i][j] == clothes[i + 1][j]) {       //같은 의상 이름이 존재할 경우 넘어감
//                        continue;
//                    } else {
//                        mapData.put(clothes[i][j], clothes[i][j + 1]);
//                    }
//                } else if (i == clothes.length - 1 && j < clothes[i].length - 1) {  //배열의 마지막 항목
//                    mapData.put(clothes[i][j], clothes[i][j + 1]);
//
//                }
//
//            }
//        }

//        Set set = mapData.entrySet();
//        Iterator iterator = set.iterator();
//
//        while(iterator.hasNext()){
//            Map.Entry entry = (Map.Entry)iterator.next();
//
//            String key = (String)entry.getKey();
//            String value = (String)entry.getValue();
//
//            if(strList.contains(value)){
//                continue;
//            }else{
//
//
//            }
//            System.out.println("key : " + key);
//            System.out.println("value : " + value);
//
//        }


        //Map mapCheck = new HashMap();
//        for (int i = 0; i < mapData.size(); i++) {
//            mapData.
//        }


        /*while(i < clothes.length * clothes[0].length){
            if()
        }*/
//        System.out.println(mapData);
        int i = 0;
        ListIterator<String> li = list.listIterator();
        res(clothes, i, mapData);

    }

    public static void res(String[][] clothes, int index, Map<String, String> maps){
        Set set = maps.entrySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();

            String key = (String)entry.getKey();
            String value = (String)entry.getValue();

            if(strList.contains(value)){
                continue;
            }else{

            }
            System.out.println("key : " + key);
            System.out.println("value : " + value);

        }

    }
}
