import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class huffmanTree
{
    //private ArrayList<node> tree;

    //传入文件名，一个node为节点的数组，得到文件中各个字符的权重，便于把修改之后的arrayList用来构造哈夫曼树
    public long getWeightAndFileSize(String filename, ArrayList<node> arrayList,HashMap<Character,Integer> codeMap){
        long fileSize=0;
        try{
            Reader readFile=new InputStreamReader(new BufferedInputStream(new FileInputStream(new File(filename))));
            //codeMap记录字符和出现次数
            char[] t=new char[1024];
            int temp=0;
            while ((temp=readFile.read(t))!=-1){
                fileSize+=temp;
                for(int i=0;i<temp;i++){
                    Character intToChar=t[i];//把读到的数字转化为字符
                    if(!codeMap.keySet().contains(intToChar)){
                        codeMap.put(intToChar,1);
                    }
                    else{
                        Integer oldValue=codeMap.get(intToChar);
                        codeMap.put(intToChar,oldValue+1);
                    }
                }

            }
            //遍历HashMap
            for(Map.Entry<Character,Integer> entry:codeMap.entrySet() ){
                node node=new node();
                data data=new data(entry.getKey(),entry.getValue());
                node.setData(data);
                arrayList.add(node);
            }

            readFile.close();

        }catch (FileNotFoundException e){
            System.out.print("未找到该文件");
        }catch (IOException e){
            System.out.print("读取文本IO异常");
        }
        return fileSize;
    }
    //冒泡排序，按降序排列，方便哈夫曼树调用
//    private void sort(ArrayList<node> arrayList){
//        int size=arrayList.size();
//        if(size==1)
//            return;
//        for(int i=0;i<size;i++){
//            for(int j=0;j<size-1-i;j++){
//                if(arrayList.get(j).getData().getWeight()<arrayList.get(j+1).getData().getWeight()){
//                    //交换
//                    node temp=arrayList.get(j);
//                    arrayList.set(j,arrayList.get(j+1));
//                    arrayList.set(j+1,temp);
//                }
//            }
//        }
//    }

    //将传入的节点数组初始化，即将其按降序排列
    private void init(ArrayList<node> arrayList){Collections.sort(arrayList);}

    //创建哈夫曼树
    public node createTree(ArrayList<node> arrayList){
        init(arrayList);
        while (arrayList.size()!=1){
            int size=arrayList.size();
            node nodeLeft=arrayList.get(size-1);
            node nodeRight=arrayList.get(size-2);
            node nodeParent=new node();
            nodeParent.setlNode(nodeLeft);
            nodeParent.setrNode(nodeRight);
            data data=new data('1',nodeLeft.getData().getWeight()+nodeRight.getData().getWeight());
//            data.setWeight(nodeLeft.getData().getWeight()+nodeRight.getData().getWeight());
            nodeParent.setData(data);
            arrayList.set(size-2,nodeParent);
            arrayList.remove(size-1);
            Collections.sort(arrayList);
        }
        //当只剩下一个节点，即构造哈夫曼树完成时，返回哈夫曼树
        return arrayList.get(0);
    }

    //得到编码表,分为两种，只有一个节点编码为零并return，第二种左0右1
    public HashMap<Character,String> getCodeMap(node rootNode){
        HashMap<Character,String> codeMap=new HashMap<Character, String>();
        if(rootNode.getlNode()==null&&rootNode.getrNode()==null){
            Character character=rootNode.getData().getC();
            codeMap.put(character,"0");
            return codeMap;
        }
        getCodeMap(rootNode,"",codeMap);
        return codeMap;
    }
    private void getCodeMap(node rootNode,String suffix,Map<Character,String> codemap){
        if(rootNode!=null){
            if (rootNode.getlNode()==null&&rootNode.getrNode()==null){
                Character character=rootNode.getData().getC();
                codemap.put(character,suffix);
            }
            getCodeMap(rootNode.getlNode(),suffix+"0",codemap);
            getCodeMap(rootNode.getrNode(),suffix+"1",codemap);
        }
    }



}


