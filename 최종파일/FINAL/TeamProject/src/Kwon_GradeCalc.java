import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


//2018110391 권진솔

class Students {
    private String ID;
    private String Name;
    private String mid;
    private String fin;
    private String score;

    Students(String id, String n, String m, String f, String g) {
        ID = id;
        Name = n;
        mid = m;
        fin = f;
        score = g;
    }

    public Students(List<String> tmpList)
    {
        ID = tmpList.get(0);
        Name = tmpList.get(1);
        mid = tmpList.get(2);
        fin  = tmpList.get(3);
        score = tmpList.get(4);
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getMid() {
        return mid;
    }

    public String getFin() {
        return fin;
    }

    public String getScore() {
        return score;
    }
}


class Kwon_GradeCalc extends JFrame implements ActionListener {

    List<Students> studentsList = new ArrayList<Students>();
    private static final int FRAME_WIDTH = 270;
    private static final int FRAME_HEIGHT = 300;
    private static final int FRAME_X_ORIGIN = 300;
    private static final int FRAME_Y_ORIGIN = 250;

    private static final String ValidID = "^[0-9]{6}$";
    private static final String ValidName = "^[a-zA-Z][a-zA-Z0-9.,$;]+$";
    private static final String ValidScore = "^[0-9]{2}$";

    private boolean IDOk = false;
    private boolean NameOk = false;
    private boolean MidScoreOk = false;
    private boolean FinScoreOk = false;
    private boolean IsCalcGrade = false;

    private int ListNumber = 0;

    private String ID;
    private String Name;
    private String Mid;
    private String Fin;
    private String grade;

    JTextField stid,stname,mids,fins,grd;

    JLabel currentData, totalData;

    public Kwon_GradeCalc() {
        
    	//기본창 처리
        setTitle("Grade Calculator");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

        Container contentPane = getContentPane();
        this.setResizable(false);
        contentPane.setBackground(Color.white);

        JMenuBar menuBar = new JMenuBar();
        JMenu filemenu = new JMenu("File");

        JMenuItem m_New = new JMenuItem("New");
        m_New.addActionListener(this);
        JMenuItem m_Open = new JMenuItem("Open");
        m_Open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                FileDialog dialog = new FileDialog((Dialog) null,"Open",FileDialog.LOAD);


                dialog.setDirectory(".");
                dialog.setVisible(true);


                if (dialog.getFile() == null) return;


                String dfName = dialog.getDirectory() + dialog.getFile();

                try {

                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader(dfName));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                    //textArea.setText("");
                    String line;

                    //파일 불러오면서 기존의 내용들 정리(삭제)
                        studentsList.removeAll(studentsList);


                    while ((line = reader.readLine()) != null) {
                        List<String> tmpList = new ArrayList<String>();
                        String array[] = line.split(",");
                        tmpList = Arrays.asList(array);
                        System.out.println(tmpList);
                        studentsList.add(new Students(tmpList));
                    }
                    reader.close();
                    stid.setText(studentsList.get(0).getID());
                    stname.setText(studentsList.get(0).getName());
                    mids.setText(studentsList.get(0).getMid());
                    fins.setText(studentsList.get(0).getFin());
                    grd.setText(studentsList.get(0).getScore());

                    currentData.setText("1");
                    totalData.setText(String.valueOf(studentsList.size()));


                    //setTitle(dialog.getFile() + " - csv");
                    setTitle(dialog.getFile() );
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        JMenuItem m_Save = new JMenuItem("Save");
        m_Save.addActionListener(this);

        JMenuItem m_quit = new JMenuItem("Quit");
        m_quit.addActionListener(this);

        JMenu Editmenu = new JMenu("Edit");

        JMenuItem Editmenuitem1 = new JMenuItem("Black");
        Editmenuitem1.addActionListener(this);

        JMenuItem Editmenuitem2 = new JMenuItem("White");
        Editmenuitem2.addActionListener(this);


        filemenu.add(m_New);
        filemenu.addSeparator();
        filemenu.add(m_Open);
        filemenu.add(m_Save);
        filemenu.addSeparator();
        filemenu.add(m_quit);


        JPanel Infos = new JPanel();
        Infos.setLayout(null);
        JLabel StudentID = new JLabel("Student ID");
        StudentID.setBounds(10, 0, 100, 40);

         stid = new JTextField("000000");
        stid.setBounds(120, 13, 120, 20);

        JLabel StudentName = new JLabel("Student Name");
        StudentName.setBounds(10, 20, 100, 40);

         stname = new JTextField("Default", 13);
        stname.setBounds(120, 33, 120, 20);
        JLabel MidScore = new JLabel("Mid Exam Score");
        MidScore.setBounds(10, 40, 100, 40);
         mids = new JTextField("0", 13);
        mids.setBounds(120, 53, 120, 20);
        JLabel FinalScore = new JLabel("Final Exam Score");
        FinalScore.setBounds(10, 60, 100, 40);
         fins = new JTextField("0", 13);
        fins.setBounds(120, 73, 120, 20);
        JLabel Grade = new JLabel("Grade Score");
        Grade.setBounds(10, 80, 100, 40);
        grd = new JTextField("F");
        grd.setBounds(120, 93, 60, 20);
        JButton calculate = new JButton("Calc");
        calculate.setBounds(180, 93, 60, 20);

        JButton AddData = new JButton("Add Data");
        AddData.setBounds(10, 120, 110, 20);
        JButton RemoveData = new JButton("Remove Data");
        RemoveData.setBounds(130, 120, 110, 20);
        JButton Prev = new JButton("Prev.");
        Prev.setBounds(10, 160, 70, 20);

         currentData = new JLabel("000");
        currentData.setBounds(100, 150, 100, 40);
        JLabel slash = new JLabel("/");
        slash.setBounds(123, 150, 100, 40);
         totalData = new JLabel("000");
        totalData.setBounds(130, 150, 100, 40);
        JButton Next = new JButton("Next");
        Next.setBounds(170, 160, 70, 20);


        Infos.add(StudentID);
        Infos.add(stid);
        Infos.add(StudentName);
        Infos.add(stname);
        Infos.add(MidScore);
        Infos.add(mids);
        Infos.add(FinalScore);
        Infos.add(fins);
        Infos.add(Grade);
        Infos.add(grd);
        Infos.add(calculate);
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mid = mids.getText();
                Fin = fins.getText();
                double totalScore = (Integer.parseInt(Mid) * 0.4f) + (Integer.parseInt(Fin) * 0.6f);
                if (totalScore >= 90) {
                    grd.setText("A");
                    grade = grd.getText();
                } else if (totalScore >= 80 && totalScore < 90) {
                    grd.setText("B");
                    grade = grd.getText();
                } else if (totalScore >= 70 && totalScore < 80) {
                    grd.setText("C");
                    grade = grd.getText();
                } else if (totalScore >= 60 && totalScore < 70) {
                    grd.setText("D");
                    grade = grd.getText();
                } else if (totalScore < 60) {
                    grd.setText("F");
                    grade = grd.getText();
                }

                IsCalcGrade = true;
            }
        });
        Infos.add(AddData);
        AddData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stid.getText().matches(ValidID)) {
                    ID = stid.getText();
                    IDOk = true;
                } else {
                    stid.setText("");
                }
                if (stname.getText().matches(ValidName)) {
                    Name = stname.getText();
                    NameOk = true;
                } else {
                    stname.setText("");
                }
                if (mids.getText().matches(ValidScore)) {
                    Mid = mids.getText();
                    MidScoreOk = true;
                } else {
                    mids.setText("");
                }
                if (fins.getText().matches(ValidScore)) {
                    Fin = fins.getText();
                    FinScoreOk = true;
                } else {
                    fins.setText("");
                }

                if (IDOk && NameOk && MidScoreOk && FinScoreOk && IsCalcGrade) {
                    studentsList.add(new Students(ID, Name, Mid, Fin, grade));
                    JOptionPane.showMessageDialog(null, "Successfully added!", "", JOptionPane.INFORMATION_MESSAGE);
                    IDOk = false;
                    NameOk = false;
                    MidScoreOk = false;
                    FinScoreOk = false;
                    IsCalcGrade = false;
                    System.out.println(studentsList);
                    if (ListNumber == 0) {
                        currentData.setText("1");

                    }


                    System.out.println("add step :" + ListNumber);
                    if (ListNumber < studentsList.size() - 1)
                        ListNumber++;
                    System.out.println("add step2 :" + ListNumber);
                    currentData.setText(String.valueOf(ListNumber + 1));
                    totalData.setText(String.valueOf(studentsList.size()));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Input!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        Infos.add(RemoveData);
        RemoveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentsList.size() != 0) {
                    System.out.println("list num :" + ListNumber);
                    studentsList.remove(ListNumber);
                    currentData.setText(String.valueOf(ListNumber));
                    totalData.setText(String.valueOf(studentsList.size()));

                    if (ListNumber == 0 && studentsList.size() > 1) {
                        System.out.println("when deleting first" + ListNumber);
                        stid.setText(studentsList.get(ListNumber + 1).getID());
                        System.out.println(studentsList.get(ListNumber + 1).getID());

                        stname.setText(studentsList.get(ListNumber + 1).getName());

                        mids.setText(studentsList.get(ListNumber + 1).getMid());

                        fins.setText(studentsList.get(ListNumber + 1).getFin());

                        grd.setText(studentsList.get(ListNumber + 1).getScore());

                        currentData.setText(String.valueOf(ListNumber + 1));
                        totalData.setText(String.valueOf(studentsList.size()));
                    } else {
                        if (ListNumber != 0) {
                            System.out.println("other condition" + ListNumber);
                            stid.setText(studentsList.get(ListNumber - 1).getID());
                            System.out.println(studentsList.get(ListNumber - 1).getID());
                            stname.setText(studentsList.get(ListNumber - 1).getName());

                            mids.setText(studentsList.get(ListNumber - 1).getMid());

                            fins.setText(studentsList.get(ListNumber - 1).getFin());

                            grd.setText(studentsList.get(ListNumber - 1).getScore());
                            currentData.setText(String.valueOf(ListNumber));
                            if(ListNumber != 0)
                                ListNumber--;
                            totalData.setText(String.valueOf(studentsList.size()));
                            System.out.println("not 0 lastcheck : " + ListNumber);
                        }
                        else if(ListNumber == 0 && studentsList.size() != 0){
                            System.out.println("if 0 check : " + ListNumber);
                            stid.setText(studentsList.get(ListNumber ).getID());
                            System.out.println(studentsList.get(ListNumber ).getID());
                            stname.setText(studentsList.get(ListNumber).getName());

                            mids.setText(studentsList.get(ListNumber ).getMid());

                            fins.setText(studentsList.get(ListNumber ).getFin());

                            grd.setText(studentsList.get(ListNumber ).getScore());
                            currentData.setText(String.valueOf(ListNumber+1));
                            totalData.setText(String.valueOf(studentsList.size()));
                            System.out.println("if 0 lastcheck : " + ListNumber);
                        }

                        else if (studentsList.size() == 0) {
                            stid.setText("000000");

                            stname.setText("Default");

                            mids.setText("0");

                            fins.setText("0");

                            grd.setText("F");
                            currentData.setText("0");
                            totalData.setText(String.valueOf(studentsList.size()));
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Empty List!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Infos.add(Prev);
        Prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ListNumber > 0) {
                    //System.out.println("List Num before : " + ListNumber);
                    ListNumber -= 1;
                    //System.out.println("List Num after: " + ListNumber);

                    //stid.setText("");
                    stid.setText(studentsList.get(ListNumber).getID());

                    stname.setText(studentsList.get(ListNumber).getName());

                    mids.setText(studentsList.get(ListNumber).getMid());

                    fins.setText(studentsList.get(ListNumber).getFin());

                    grd.setText(studentsList.get(ListNumber).getScore());

                    currentData.setText(String.valueOf(ListNumber + 1));
                    totalData.setText(String.valueOf(studentsList.size()));
                    System.out.println("List Num end: " + ListNumber);
                } else {
                    JOptionPane.showMessageDialog(null, "Empty List!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        Infos.add(currentData);
        Infos.add(slash);
        Infos.add(totalData);

        Infos.add(Next);
        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((ListNumber + 1) < studentsList.size()) {
                    ListNumber += 1;
                    stid.setText(studentsList.get(ListNumber).getID());
                    //System.out.println(studentsList.get(ListNumber).getID());
                    //stname.setText("");
                    stname.setText(studentsList.get(ListNumber).getName());
                    //System.out.println(studentsList.get(ListNumber).getName());
                    //mids.setText("");
                    mids.setText(studentsList.get(ListNumber).getMid());
                    //System.out.println(studentsList.get(ListNumber).getMid());
                    //fins.setText("");
                    fins.setText(studentsList.get(ListNumber).getFin());
                    //System.out.println(studentsList.get(ListNumber).getFin());

                    grd.setText(studentsList.get(ListNumber).getScore());

                    currentData.setText(String.valueOf(ListNumber + 1));
                    totalData.setText(String.valueOf(studentsList.size()));
                    //System.out.println("List Num : " + ListNumber);
                } else {
                    JOptionPane.showMessageDialog(null, "End of the List!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        add(Infos);


        Editmenu.add(Editmenuitem1);
        filemenu.addSeparator();
        Editmenu.add(Editmenuitem2);


        menuBar.add(filemenu);
        menuBar.add(Editmenu);

        setJMenuBar(menuBar);

        
        setDefaultCloseOperation(HIDE_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Container contentPane = getContentPane();

        String menuName;
        menuName = event.getActionCommand();


        if (menuName == "Quit") {
            //System.exit(0);
        	dispose();
        } else if (menuName == "New") {

            //textArea.setText("");
            stid.setText("");
            stname.setText("");
            mids.setText("");
            fins.setText("");
            grd.setText("");
        }

            else if (menuName == "Save") {

            FileDialog dialogsave = new FileDialog(this, "Save", FileDialog.SAVE);


            dialogsave.setDirectory(".");
            dialogsave.setVisible(true);


            if (dialogsave.getFile() == null) return;


            String dfNamesave = dialogsave.getDirectory() + dialogsave.getFile();
            // System.out.println(dfName);


            try {

                BufferedWriter writer = new BufferedWriter(new FileWriter(dfNamesave));

                //writer.write(textArea.getText());
                for(Students inputStudent : studentsList)
                {
                    writer.write(inputStudent.getID() + "," + inputStudent.getName()+","+inputStudent.getMid()
                                +","+inputStudent.getFin()+","+inputStudent.getScore());
                    writer.newLine();
                }
                writer.close();

                //setTitle(dialogsave.getFile() + " - csv");
                setTitle(dialogsave.getFile() );
                
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(this, "save error");
            }
        } else if (menuName == "Black") {

            stid.setBackground(Color.black);
            stid.setForeground(Color.GREEN);
            stname.setBackground(Color.black);
            stname.setForeground(Color.GREEN);
            mids.setBackground(Color.black);
            mids.setForeground(Color.GREEN);
            fins.setBackground(Color.black);
            fins.setForeground(Color.GREEN);
            grd.setBackground(Color.black);
            grd.setForeground(Color.GREEN);

        } else if (menuName == "White") {

            stid.setBackground(Color.white);
            stid.setForeground(Color.black);
            stname.setBackground(Color.white);
            stname.setForeground(Color.black);
            mids.setBackground(Color.white);
            mids.setForeground(Color.black);
            fins.setBackground(Color.white);
            fins.setForeground(Color.black);
            grd.setBackground(Color.white);
            grd.setForeground(Color.black);

        }


    }

    public static void main(String[] args) {
        Kwon_GradeCalc myFrame;

        myFrame = new Kwon_GradeCalc();
        myFrame.setVisible(true);


    }
}