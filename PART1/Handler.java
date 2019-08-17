//package com.own;


import java.io.*;
import static java.lang.Math.random;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Handler implements  Runnable {

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private BufferedReader reader;
    private PrintWriter writer;
    private BufferedWriter out;

    public Handler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            //File dir = new File(this.getClass().getResource("/").getPath());
            //WEB_ROOT = dir.getName();
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintWriter(new OutputStreamWriter(outputStream));



            File directory = new File("");
            String WEB_ROOT = directory.getAbsolutePath();
           // System.out.println("temp path= "+temp);


            Path path = Paths.get(WEB_ROOT + "/poem.txt");
            //System.out.println(path.toString());
            byte[] data =  Files.readAllBytes(path);
            String id="";
            String title="";
            String author="";
            ArrayList<poem> list=new ArrayList<poem>();
            FileReader fr=new FileReader(WEB_ROOT + "/poem.txt");
            BufferedReader br=new BufferedReader(fr);
            String line="";
            StringBuffer text = new StringBuffer();
            while ((line=br.readLine())!=null) {
                //System.out.println(line);
                    if(line.contains("@@poemid=")){
                        id=line.replace("@@poemid=", "");
                        //System.out.println(id);
                    }else if(line.contains("@@title=")){
                        title=line.replace("@@title=", "");
                        //System.out.println(title);
                    }else if(line.contains("BY")){
                        author=line.replace("BY ", "");
                        //System.out.println(author);
                    }else{
                        if(!line.contains("@@endpoem")){
                            text.append(line);
                            text.append("\r\n");
                        }
                    }
                    //System.out.println(line);
                   if(line.contains("@@endpoem")){
                       poem entity = new poem(title,id,author,text.toString());
                       //System.out.println(entity.getTitle());
                       list.add(entity);
                       text.delete(0, text.length());
                   }
            }
            br.close();
            fr.close();
            String welcome="Welcome to the poem.\nEnter help for more information or poem for a "
                    + "random poem\nEnter poem id=# to diesplay one of the following poems";
            outputStream.write(welcome.getBytes());
            for(int i =0;i<list.size();i++){
                String tem=i+"--:";
                outputStream.write(tem.getBytes());
                outputStream.write(list.get(i).getTitle().getBytes());
                outputStream.write("\r\n".getBytes());
            }
            outputStream.write("\r\n".getBytes());
            //System.out.println("list size is= "+ list.size());
            String msg =reader.readLine();
            if(msg.contains("poem id")&&!msg.contains(",")){
                id=msg.replace("poem id=", "");
                System.out.println("is here");
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getId().equals(id)){

                        outputStream.write("\r\n".getBytes());
                        outputStream.write(list.get(i).getTitle().getBytes());
                        outputStream.write("\r\n".getBytes());
                        outputStream.write(list.get(i).getAuthor().getBytes());
                        outputStream.write("\r\n".getBytes());
                        outputStream.write("--------------------".getBytes());
                        outputStream.write("\r\n".getBytes());
                        outputStream.write(list.get(i).getContent().getBytes());

                    }

                }
            }else if(msg.equals("poem")){
                Random a =new Random();
                int i=a.nextInt(9)+1;
                outputStream.write("\r\n".getBytes());
                outputStream.write(list.get(i).getTitle().getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(list.get(i).getAuthor().getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write("--------------------".getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(list.get(i).getContent().getBytes());
            }else if(msg.contains(",")){
                line=msg.replace("poem id=", "");
                String []str=line.split(",");
                System.out.println(str[0]);
                for(int i=0;i<str.length;i++){
                    System.out.println(str[i]);
                    int index=Integer.parseInt(str[i]);
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(list.get(index).getTitle().getBytes());
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(list.get(index).getAuthor().getBytes());
                    outputStream.write("----------------------".getBytes());
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(list.get(index).getContent().getBytes());
                }
            }else if(msg.contains("poem n=")){
                 int number=Integer.parseInt(msg.replace("poem n=", ""));
                 for(int i=0;i<number;i++){
                     Random a =new Random();
                    int index=a.nextInt(9)+1;
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(list.get(index).getTitle().getBytes());
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(list.get(index).getAuthor().getBytes());
                    outputStream.write("\r\n".getBytes());
                    outputStream.write("--------------------".getBytes());
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(list.get(index).getContent().getBytes());
                 }
            }else if(msg.contains("poem maxlines")){
                int number=Integer.parseInt(msg.replace("poem maxlines=", ""));
                for(int i=0;i<list.size();i++){
                   String []str=list.get(i).getContent().split("\r\n");
                   System.out.println(str.length);
                   if(str.length<number){
                       outputStream.write("\r\n".getBytes());
                       outputStream.write(list.get(i).getTitle().getBytes());
                       outputStream.write("\r\n".getBytes());
                       outputStream.write(list.get(i).getAuthor().getBytes());
                       outputStream.write("\r\n".getBytes());
                       outputStream.write(list.get(i).getContent().getBytes());
                   }
                }
            }else if(msg.contains("poem minlines")){
                int number=Integer.parseInt(msg.replace("poem minlines=", ""));
                for(int i=0;i<list.size();i++){
                   String []str=list.get(i).getContent().split("\r\n");
                   System.out.println(str.length);
                   if(str.length>number){
                       outputStream.write("\r\n".getBytes());
                       outputStream.write(list.get(i).getTitle().getBytes());
                       outputStream.write("\r\n".getBytes());
                       outputStream.write(list.get(i).getAuthor().getBytes());
                       outputStream.write("\r\n".getBytes());
                       outputStream.write(list.get(i).getContent().getBytes());
                   }
                }
            }else if(msg.equals("help")){
                outputStream.write("\r\n".getBytes());
                outputStream.write("additional request".getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write("poem id=1,2,3 which return ID with 1 2 3".getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write("poem n=3 which returns 3 poems".getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write("poem maxlines=50 return poems  which's line < 50 number can be vary".getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write("poem minlines=50 return poems  which's line > 50 number can be vary".getBytes());
            }


           // System.out.println(list.get(1).getContent());


            //outputStream.write(data);

            outputStream.close();
            inputStream.close();
            reader.close();

        }catch (IOException e){

            writer.write("HTTP/1.1 404 ERROR:FILE NOT FINDED");
            writer.close();
        }
    }
}
