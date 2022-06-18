package com.example.abconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {


    private EditText display;
    private TextView bin;
    private TextView dec;
    private TextView oct;
    private TextView hex;
    private Button button;
    private Button info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        bin = findViewById(R.id.bin);
        dec = findViewById(R.id.dec);
        oct = findViewById(R.id.oct);
        hex = findViewById(R.id.hex);
        button = findViewById(R.id.button);
        info = (Button) findViewById(R.id.info);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoactivity();
            }
        });




        Spinner sp = findViewById(R.id.sp1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.base, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        


        Button but =  (Button)findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Spinner sp11 =(Spinner)findViewById(R.id.sp1);
                String text = sp.getSelectedItem().toString();


                if (text.equals("Octal"))
                    octal(display.getText().toString());

                if (text.equals("Binary"))
                    binary(display.getText().toString());

                if (text.equals("Hexadecimal"))
                    hexd(display.getText().toString());

                if (text.equals("Decimal"))
                    decimal(display.getText().toString());



            }
        });

        display.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String userinput =display.getText().toString().trim();
                button.setEnabled(!userinput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void infoactivity(){
        Intent intent=new Intent(this,InfoActivity.class);
        startActivity(intent);
        }

    public void decimal(String n1) {
        n1=n1.replaceAll("\\s","");


        int i, a = 0, k1 = 0, l = n1.length();
        for (i = 0; i < l; i++) {
            char ch1 = n1.charAt(i);
            if (Character.isDigit(n1.charAt(i)) == false && ch1 != '.')
                a = 1;
        }
        if (a == 1) {
            Toast.makeText(getApplicationContext(), "Enter a Decimal number", Toast.LENGTH_SHORT).show();
            display.setText("");

        } else {

            for (i = 0; i < l; i++) {
                char ch = n1.charAt(i);
                if (ch == '.') {
                    k1 = 1;

                }
            }
            if (k1 == 1) {
                try {
                    float s = Float.parseFloat(n1);

                    int pri = (int) s;

                    float sec = s - pri;
                    String r = "";
                    while (pri != 0) {
                        int d1 = pri % 2;
                        r = d1 + r;
                        pri = pri / 2;

                    }
                    r += ('.');

                    for (i = 1; i <= 5; i++) {
                        sec *= 2;
                        int fract = (int) sec;

                        if (fract == 1) {
                            sec -= fract;
                            r += '1';
                        } else {
                            r += '0';
                        }

                    }
                    bin.setText(r);


                    r = "";
                    s = Float.parseFloat(n1);
                    pri = (int) s;
                    sec = s - pri;
                    while (pri != 0) {
                        int d1 = pri % 8;
                        r = d1 + r;
                        pri = pri / 8;

                    }
                    r += ('.');

                    for (i = 1; i <= 5; i++) {
                        sec *= 8;
                        int fract = (int) sec;

                        if (fract > 0) {
                            sec -= fract;
                            r += fract;
                        } else {
                            r += '0';
                        }

                    }
                    oct.setText(r);

                    r = "";
                    s = Float.parseFloat(n1);
                    pri = (int) s;
                    sec = s - pri;
                    while (pri != 0) {
                        int d1 = pri % 16;
                        if (d1 == 10)
                            r = 'A' + r;
                        else if (d1 == 11)
                            r = 'B' + r;
                        else if (d1 == 12)
                            r = 'C' + r;
                        else if (d1 == 13)
                            r = 'D' + r;
                        else if (d1 == 14)
                            r = 'E' + r;
                        else if (d1 == 15)
                            r = 'F' + r;
                        else
                            r = d1 + r;
                        pri = pri / 16;

                    }
                    r += ('.');

                    for (i = 1; i <= 5; i++) {
                        sec *= 16;
                        int fract = (int) sec;

                        if (fract > 0) {
                            sec -= fract;
                            if (fract == 10)
                                r = r + 'A';
                            else if (fract == 11)
                                r = r + 'B';
                            else if (fract == 12)
                                r = r + 'C';
                            else if (fract == 13)
                                r = r + 'D';
                            else if (fract == 14)
                                r = r + 'E';
                            else if (fract == 15)
                                r = r + 'F';
                            else
                                r = r + fract;
                        } else {
                            r += '0';
                        }
                    }
                    hex.setText(r);
                    dec.setText(n1);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Enter a DecimalFraction number", Toast.LENGTH_SHORT).show();
                    display.setText("");
                }
            } else {


                long n2 = Long.parseLong(n1);
                long n = n2;
                String r = "";
                while (n != 0) {
                    long d = n % 2;
                    r = d + r;
                    n = n / 2;

                }
                bin.setText(r);

                dec.setText(n1);

                r = "";
                n = n2;
                while (n != 0) {
                    long d = n % 8;
                    r = d + r;
                    n = n / 8;

                }
                oct.setText(r);

                n = n2;
                r = "";
                while (n != 0) {
                    long d = n % 16;
                    if (d == 10)
                        r = 'A' + r;
                    else if (d == 11)
                        r = 'B' + r;
                    else if (d == 12)
                        r = 'C' + r;
                    else if (d == 13)
                        r = 'D' + r;
                    else if (d == 14)
                        r = 'E' + r;
                    else if (d == 15)
                        r = 'F' + r;
                    else
                        r = d + r;
                    n = n / 16;
                }
                hex.setText(r);

            }
        }
    }

    public void octal(String n1) {


        n1 = n1.toUpperCase();
        int i, a = 0, k1 = 0, k = 0, s1 = 0, l = n1.length();

        for (i = 0; i < l; i++) {
            char ch = n1.charAt(i);
            if (((int) ch) > 55 && ch != '.')
                a = 1;
        }
        if (a == 1) {
            Toast.makeText(getApplicationContext(), "Enter a Octal number", Toast.LENGTH_SHORT).show();
            display.setText("");
        } else {
            for (i = 0; i < l; i++) {
                char ch = n1.charAt(i);
                if (ch == '.') {
                    k1 = 1;

                }
            }
            if (k1 == 1) {
                try {


                    float s = Float.parseFloat(n1);
                    int pri = (int) s;

                    String r = "";
                    String g = "";
                    double s2 = 0.0;
                    while (pri != 0) {
                        int d = pri % 10;
                        s1 = s1 + (d * (int) Math.pow(8, k));
                        k++;
                        pri = pri / 10;
                    }
                    int k2 = n1.lastIndexOf('.');
                    g = n1.substring((k2 + 1));
                    int length = g.length();
                    int len1 = length;
                    int g1 = Integer.parseInt(g);
                    while (length >= 1) {
                        int d = g1 % 10;
                        s2 = s2 + (d * (1 / Math.pow(8, length)));
                        length--;
                        g1 = g1 / 10;
                    }
                    double s3 = s1 + s2;
                    r = Double.toString(s3);
                    String s4 = r;
                    dec.setText(r);

                    oct.setText(n1);

                    s = Float.parseFloat(r);
                    pri = (int) s;
                    float sec = s - pri;
                    String r1 = "";
                    while (pri != 0) {
                        int d1 = pri % 2;
                        r1 = d1 + r1;
                        pri = pri / 2;

                    }
                    int nod = 3;
                    r1 += ('.');
                    for (int j = 1; j <= len1; j++) {
                        nod = 3;
                        nod = nod * j;

                    }

                    for (i = 1; i <= nod; i++) {
                        sec *= 2;
                        int fract = (int) sec;

                        if (fract == 1) {
                            sec -= fract;
                            r1 += '1';
                        } else {
                            r1 += '0';
                        }

                    }
                    bin.setText(r1);


                    s = Float.parseFloat(r);
                    r1 = "";
                    pri = (int) s;
                    sec = s - pri;
                    while (pri != 0) {
                        int d1 = pri % 16;
                        if (d1 == 10)
                            r1 = 'A' + r1;
                        else if (d1 == 11)
                            r1 = 'B' + r1;
                        else if (d1 == 12)
                            r1 = 'C' + r1;
                        else if (d1 == 13)
                            r1 = 'D' + r1;
                        else if (d1 == 14)
                            r1 = 'E' + r1;
                        else if (d1 == 15)
                            r1 = 'F' + r1;
                        else
                            r1 = d1 + r1;
                        pri = pri / 16;

                    }
                    r1 += ('.');


                    for (i = 1; i <= len1; i++) {
                        sec *= 16;
                        int fract = (int) sec;

                        if (fract > 0) {
                            sec -= fract;
                            if (fract == 10)
                                r1 = r1 + 'A';
                            else if (fract == 11)
                                r1 = r1 + 'B';
                            else if (fract == 12)
                                r1 = r1 + 'C';
                            else if (fract == 13)
                                r1 = r1 + 'D';
                            else if (fract == 14)
                                r1 = r1 + 'E';
                            else if (fract == 15)
                                r1 = r1 + 'F';
                            else
                                r1 = r1 + fract;
                        } else {
                            r1 += '0';
                        }
                    }
                    hex.setText(r1);


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Enter a OctalFraction number", Toast.LENGTH_SHORT).show();
                    display.setText("");
                }
            } else {


                long n2 = Long.parseLong(n1);
                long n = n2;
                long s = 0;
                long p1 = 0;
                String st1;
                String r = "";

                long deci = Long.parseLong(n1, 8);
                st1 = Long.toString(deci);
                dec.setText(st1);


                n = deci;
                r = "";
                while (n != 0) {
                    long d = n % 2;
                    r = d + r;
                    n = n / 2;
                }
                bin.setText(r);
                oct.setText(n1);

                n2 = Long.parseLong(n1);
                n = n2;
                p1 = 0;
                s = 0;
                r = "";
                while (n != 0) {
                    long d = n % 10;
                    s = s + (d * (long) Math.pow(8, p1));
                    p1++;
                    n = n / 10;
                }
                while (s != 0) {
                    long d1 = s % 16;

                    if (d1 == 10)
                        r = 'A' + r;
                    else if (d1 == 11)
                        r = 'B' + r;
                    else if (d1 == 12)
                        r = 'C' + r;
                    else if (d1 == 13)
                        r = 'D' + r;
                    else if (d1 == 14)
                        r = 'E' + r;
                    else if (d1 == 15)
                        r = 'F' + r;
                    else
                        r = d1 + r;
                    s = s / 16;
                }
                hex.setText(r);

            }
        }
    }

    public void binary(String n1) {
        n1=n1.replaceAll("\\s","");
        int i, a = 0, k1 = 0, k = 0, s1 = 0, l = n1.length();

        for (i = 0; i < l; i++) {
            char ch = n1.charAt(i);
            if (ch != '0' && ch != '1' && ch != '.')
                a = 1;
        }
        if (a == 1) {
            Toast.makeText(getApplicationContext(), "Enter a Binary number", Toast.LENGTH_SHORT).show();
            display.setText("");
        } else {
            for (i = 0; i < l; i++) {
                char ch = n1.charAt(i);
                if (ch == '.') {
                    k1 = 1;

                }
            }
            if (k1 == 1) {
                try {


                    float s = Float.parseFloat(n1);
                    int pri = (int) s;

                    String r = "";

                    String g = "";
                    double s2 = 0.0;
                    while (pri != 0) {
                        int d = pri % 10;
                        s1 = s1 + (d * (int) Math.pow(2, k));
                        k++;
                        pri = pri / 10;
                    }
                    int k2 = n1.lastIndexOf('.');
                    g = n1.substring((k2 + 1));
                    int length = g.length();
                    int len1 = length;
                    int g1 = Integer.parseInt(g);
                    while (length >= 1) {
                        int d = g1 % 10;
                        s2 = s2 + (d * (1 / Math.pow(2, length)));
                        length--;
                        g1 = g1 / 10;
                    }
                    double s3 = s1 + s2;
                    r = Double.toString(s3);
                    String s4 = r;
                    dec.setText(r);

                    s = Float.parseFloat(r);

                    String r1 = "";
                    pri = (int) s;
                    float sec = s - pri;
                    while (pri != 0) {
                        int d1 = pri % 16;
                        if (d1 == 10)
                            r1 = 'A' + r1;
                        else if (d1 == 11)
                            r1 = 'B' + r1;
                        else if (d1 == 12)
                            r1 = 'C' + r1;
                        else if (d1 == 13)
                            r1 = 'D' + r1;
                        else if (d1 == 14)
                            r1 = 'E' + r1;
                        else if (d1 == 15)
                            r1 = 'F' + r1;
                        else
                            r1 = d1 + r1;
                        pri = pri / 16;

                    }
                    r1 += ('.');


                    for (i = 1; i <= len1; i++) {
                        sec *= 16;
                        int fract = (int) sec;

                        if (fract > 0) {
                            sec -= fract;
                            if (fract == 10)
                                r1 = r1 + 'A';
                            else if (fract == 11)
                                r1 = r1 + 'B';
                            else if (fract == 12)
                                r1 = r1 + 'C';
                            else if (fract == 13)
                                r1 = r1 + 'D';
                            else if (fract == 14)
                                r1 = r1 + 'E';
                            else if (fract == 15)
                                r1 = r1 + 'F';
                            else
                                r1 = r1 + fract;
                        } else {
                            r1 += '0';
                        }
                    }
                    hex.setText(r1);

                    bin.setText(n1);

                    r1 = "";
                    s = Float.parseFloat(r);
                    pri = (int) s;
                    sec = s - pri;
                    while (pri != 0) {
                        int d1 = pri % 8;
                        r1 = d1 + r1;
                        pri = pri / 8;

                    }
                    r1 += ('.');

                    for (i = 1; i <= len1; i++) {
                        sec *= 8;
                        int fract = (int) sec;

                        if (fract > 0) {
                            sec -= fract;
                            r1 += fract;
                        } else {
                            r1 += '0';
                        }

                    }
                    oct.setText(r1);


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Enter a BinaryFraction number", Toast.LENGTH_SHORT).show();
                    display.setText("");
                }
            } else {

                long n2 = Long.parseLong(n1);
                long n = n2;

                k = 0;
                long s = 0;
                String r = "";
                while (n != 0) {
                    long d = n % 10;
                    s = s + (d * (long) Math.pow(2, k));
                    k++;
                    n = n / 10;
                }
                String st1 = Long.toString(s);
                dec.setText(st1);

                bin.setText(n1);

                n2 = Long.parseLong(n1);

                n = n2;
                k = 0;
                s = 0;
                r = "";
                while (n != 0) {
                    long d = n % 10;
                    s = s + (d * (long) Math.pow(2, k));
                    k++;
                    n = n / 10;
                }
                while (s != 0) {
                    long d1 = s % 8;
                    r = d1 + r;
                    s = s / 8;

                }
                oct.setText(r);

                n2 = Integer.parseInt(n1);
                n = n2;

                k = 0;
                s = 0;
                r = "";
                while (n != 0) {
                    long d = n % 10;
                    s = s + (d * (long) Math.pow(2, k));
                    k++;
                    n = n / 10;
                }
                while (s != 0) {
                    long d1 = s % 16;
                    if (d1 == 10)
                        r = 'A' + r;
                    else if (d1 == 11)
                        r = 'B' + r;
                    else if (d1 == 12)
                        r = 'C' + r;
                    else if (d1 == 13)
                        r = 'D' + r;
                    else if (d1 == 14)
                        r = 'E' + r;
                    else if (d1 == 15)
                        r = 'F' + r;
                    else
                        r = d1 + r;
                    s = s / 16;
                }
                hex.setText(r);


            }
        }
    }


    public void hexd(String n1) {
        n1=n1.replaceAll("\\s","");


        n1 = n1.toUpperCase();
        int i, a = 0, k1 = 0, k4 = 0, k3 = 0, k = 0, s1 = 0, l = n1.length();
        int l1=l;
        for (i = 0; i < l; i++) {
            char ch = n1.charAt(i);
            if (((int)ch>=48 && (int) ch <= 57) || ((int)ch>=65 && (int) ch <= 70) || (ch == '.'))
                a++;
        }
        if (a!=l1) {
            Toast.makeText(getApplicationContext(), "Enter a hexadecimal number", Toast.LENGTH_SHORT).show();
            display.setText("");

        } else {

            for (i = 0; i < l; i++) {
                char ch = n1.charAt(i);

                if (ch == '.')
                    k1++;
            }
            if (k1 > 1 ) {
                Toast.makeText(getApplicationContext(), "Enter a hexadecimalfraction number", Toast.LENGTH_SHORT).show();
                display.setText("");
            } else {

                if (k1 == 1) {
                    String r = "";
                    String g = "";
                    String g1 = "";
                    double s2 = 0.0;
                    int k2 = n1.lastIndexOf('.');
                    g1 = n1.substring(0, k2);
                    g = n1.substring((k2 + 1));

                    s1 = Integer.parseInt(g1, 16);

                    int j = 1;
                    for (i = 0; i < g.length(); i++, j++) {
                        char ch = g.charAt(i);
                        if (ch >= '0' && ch <= '9')
                            s2 = s2 + (((int) ch - 48) * (1 / Math.pow(16, j)));
                        else
                            s2 = s2 + (((int) ch - 55) * (1 / Math.pow(16, j)));
                    }
                    double s3 = s1 + s2;
                    r = Double.toString(s3);


                    dec.setText(r);

                    k2 = r.lastIndexOf('.');
                    g1 = r.substring(0, k2);
                    g = r.substring((k2 + 1));

                    int s = Integer.parseInt(g1);
                    float ori = Float.parseFloat(r);
                    float sec = ori - s;
                    String r1 = "";
                    int length = g.length();
                    int len1 = length;
                    while (s != 0) {
                        int d1 = s % 2;
                        r1 = d1 + r1;
                        s = s / 2;
                    }

                    r1 += ('.');


                    for (i = 1; i <= 5; i++) {
                        sec *= 2;
                        int fract = (int) sec;

                        if (fract == 1) {
                            sec -= fract;
                            r1 += '1';
                        } else {
                            r1 += '0';
                        }

                    }
                    bin.setText(r1);

                    k2 = r.lastIndexOf('.');
                    g1 = r.substring(0, k2);
                    g = r.substring((k2 + 1));
                    r1 = "";
                    s = Integer.parseInt(g1);
                    ori = Float.parseFloat(r);
                    sec = ori - s;

                    while (s != 0) {
                        int d1 = s % 8;
                        r1 = d1 + r1;
                        s = s / 8;

                    }
                    r1 += ('.');

                    for (i = 1; i <= 5; i++) {
                        sec *= 8;
                        int fract = (int) sec;

                        if (fract > 0) {
                            sec -= fract;
                            r1 += fract;
                        } else {
                            r1 += '0';
                        }

                    }
                    oct.setText(r1);

                    hex.setText(n1);


                } else {

                    n1 = n1.toUpperCase();
                    long deci = Long.parseLong(n1, 16);


                    String st1 = Long.toString(deci);
                    dec.setText(st1);


                    long n = deci;
                    String r = "";
                    while (n != 0) {
                        long d = n % 2;
                        r = d + r;
                        n = n / 2;

                    }
                    bin.setText(r);

                    r = "";
                    n = deci;
                    while (n != 0) {
                        long d = n % 8;
                        r = d + r;
                        n = n / 8;

                    }
                    oct.setText(r);

                    hex.setText(n1);

                }
            }
        }
    }


}







