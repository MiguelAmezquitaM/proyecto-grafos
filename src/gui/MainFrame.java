/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import datos.Ciudad;
import datos.Viaje;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import grafo.Grafo;
import grafo.GrafoD;
import gui.util.Vector2D;
import persist.ArchivoProyecto;
/**
 * @author david
 */
public class MainFrame extends javax.swing.JFrame {
    Grafo<Ciudad, Viaje> grafo = new GrafoD<>();
    /**
     * Creates new form MainFrame
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     */
    public MainFrame() throws FileNotFoundException, ClassNotFoundException, IOException {
        initComponents();
    }

    private void initComponents() throws FileNotFoundException, ClassNotFoundException, IOException {
        ArchivoProyecto archivoProyecto = new ArchivoProyecto();
        File f = new File("grafo.obj");
        grafo = f.exists() ? archivoProyecto.read() : grafo;

        JPanel canvas = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Lienzo.dibujarGrafo((Graphics2D) g, grafo);
            }
        };

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                canvas.setSize(e.getComponent().getSize());
            }
        });

        setTitle("Proyecto grafos");
        setResizable(true);
        setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 720);
        setBackground(new Color(19, 141, 117, 255));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        canvas.setBounds(new Rectangle(0, 0, 1200, 720));
        canvas.setBackground(new java.awt.Color(30, 132, 73, 255));

        MouseAdapter ml = new MyMouseListener(canvas, grafo);
        WindowAdapter w1 = new MyWindowListener(archivoProyecto, grafo);  
        canvas.addMouseListener(ml);
        canvas.addMouseMotionListener(ml);
        canvas.addMouseWheelListener(ml);
        addWindowListener(w1);
        add(canvas);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame f;
            try {
                f = new MainFrame();
                f.setVisible(true);
            } catch (ClassNotFoundException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        });
    }
}

class MyMouseListener extends MouseAdapter {
    Ciudad selected = null;

    private final Grafo<Ciudad, Viaje> grafo;
    private int n = 0;
    int nodo1 = -1, nodo2 = -1;
    JPanel panel;
    Graphics2D g;

    public MyMouseListener(JPanel panel, Grafo<Ciudad, Viaje> grafo) {
        this.grafo = grafo;
        this.panel = panel;
        g = (Graphics2D) this.panel.getGraphics();
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        g = (Graphics2D) panel.getGraphics();
        if (evt.getButton() == 1) {
            boolean haynodo = false;
            var ci = Lienzo.hayCiudadEn(grafo, evt.getX(), evt.getY());

            if (ci != -1) {
                var c = grafo.getVertice(ci);
                var p = c.getPosition();
                Lienzo.clickSobreNodo(g, p.x, p.y, Color.green);
                haynodo = true;
                n++;
                if (nodo1 == -1 && nodo2 == -1) {
                    nodo1 = ci;
                } else {
                    nodo2 = ci;
                }
            }

            if (!haynodo) {
                String nombre, pais;
                try {
                    nombre = JOptionPane.showInputDialog("Nombre de la ciudad: "); if (nombre == null) throw new Exception();
                    pais = JOptionPane.showInputDialog("Nombre del pais: "); if (pais == null) throw new Exception();
                } catch (Exception e) {
                    return;
                }

                grafo.addVertice(new Ciudad(nombre, pais, new Vector2D(evt.getX(), evt.getY())));
                Lienzo.pintarCirculo(g, nombre, evt.getX(), evt.getY());
            }
            if (n == 2) {
                double costo, distancia; int tiempo;
                try {
                    costo = Double.parseDouble(JOptionPane.showInputDialog("Costo: "));
                    distancia = Double.parseDouble(JOptionPane.showInputDialog("Distancia: "));
                    tiempo = Integer.parseInt(JOptionPane.showInputDialog("Tiempo: "));
                } catch (Exception e) {
                    return;
                }
                grafo.addCosto(nodo1, nodo2, new Viaje(costo, distancia, tiempo));
                Lienzo.modified.add(nodo1);
                Lienzo.modified.add(nodo2);

                nodo1 = nodo2 = -1;
                n = 0;

                panel.paint(g);
            }
        }
    }

    @Override
    
    public void mousePressed(MouseEvent e) {
        oldMousePosition.x = e.getX(); oldMousePosition.y = e.getY();
        var ci = Lienzo.hayCiudadEn(grafo, e.getX(), e.getY());

        if (ci != -1) {
            selected = grafo.getVertice(ci);
            return;
        }

        selected = null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selected = null;
    }

    Vector2D oldMousePosition = new Vector2D(0, 0);

    @Override
    public void mouseDragged(MouseEvent e) {
        g = (Graphics2D) panel.getGraphics();

        if (selected == null) {
            Lienzo.moverCamara(e.getX() - oldMousePosition.x, e.getY() - oldMousePosition.y);
            oldMousePosition.x = e.getX();
            oldMousePosition.y = e.getY();
            panel.paint(g);
            return;
        }

        selected.setPosition(Lienzo.pos(e.getX(), e.getY()));
        panel.paint(g);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        g = (Graphics2D) panel.getGraphics();
        super.mouseWheelMoved(e);
        var rot = e.getPreciseWheelRotation();
        Lienzo.setScale(0.05 * rot);
        panel.paint(g);
        for (int i = 0; i < grafo.orden(); i++) {
            Lienzo.modified.add(i);
        }
    }
}

class MyWindowListener extends WindowAdapter {
    ArchivoProyecto ar = new ArchivoProyecto();
    private Grafo<Ciudad,Viaje> grafo;
    public MyWindowListener(ArchivoProyecto archivoProyecto, Grafo<Ciudad, Viaje> grafo) {
        this.ar = archivoProyecto;
        this.grafo = grafo;
    }
    @Override
    public void windowClosed(WindowEvent e){
        try {
            ar.write(grafo);
        } catch (IOException e1) {
            System.out.println("no se guardo");
            e1.printStackTrace();
        }
    }
}