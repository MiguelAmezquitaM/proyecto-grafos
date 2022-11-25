/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import datos.Ciudad;
import datos.TimeIndicator;
import datos.Viaje;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import grafo.DFS;
import grafo.Floyd;
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
     *
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
                g.dispose();
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
        canvas.setBackground(new java.awt.Color(27, 38, 49, 255));

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

    private Floyd<Ciudad, Viaje> floyd = null;

    private List<Ciudad> dfsResult = null;

    public MyMouseListener(JPanel panel, Grafo<Ciudad, Viaje> grafo) {
        this.grafo = grafo;
        this.panel = panel;
    }

    public List<Integer> masSalidas(Grafo<Ciudad,Viaje> grafo) {
        List<Integer> masSalidas = new ArrayList<>();
        int mayor = -1;
        for (int i = 0; i < grafo.orden(); i++) {
            if (grafo.getSucesores(i).size() > mayor) {
                mayor = grafo.getSucesores(i).size();
                masSalidas.clear();
                masSalidas.add(i);
            }
            if (grafo.getSucesores(i).size() == mayor) {
                masSalidas.add(i);
            }
        }
        return masSalidas;
    }

    private void drawPath(int i, int destiny) {
        if (i == destiny) {
            return;
        }
        int pre = floyd.getRecorridos()[i][destiny];
        if (pre == -1) {
            JOptionPane.showMessageDialog(panel, "No existe una ruta");
            return;
        }
        int j = floyd.getRecorridos()[i][pre];
        if (j == -1) return;
        Vector2D c1p = grafo.getVertice(i).getPosition();
        Vector2D c2p = grafo.getVertice(j).getPosition();
        Viaje viaje = grafo.getCosto(i, j);
        Lienzo.pintarFlecha(g, c1p.x, c1p.y, c2p.x, c2p.y, viaje, Color.red);
        drawPath(j, destiny);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        g = (Graphics2D) panel.getGraphics();

        if (floyd != null) {
            int destiny = Lienzo.hayCiudadEn(grafo, evt.getX(), evt.getY());
            if (destiny == -1) {
                floyd = null;
                return;
            }

            drawPath(PopupMenu.selected, destiny);

            floyd = null;
            return;
        }

        if (evt.getButton() == MouseEvent.BUTTON1) {
            int op = PopupMenu.click(evt.getX(), evt.getY());
            if (op != -1) {
                if (op == 0) {
                    grafo.removeVertice(PopupMenu.selected);
                }
                else if (op == 1) {
                    grafo.aislar(PopupMenu.selected);
                }
                else if (op == 2) {
                    floyd = new Floyd<>(grafo, new TimeIndicator(), Viaje.class);
                }
                else if (op == 3) {
                    DFS<Ciudad, Viaje> dfs = new DFS<>();
                    dfsResult = dfs.recorridoDFS(grafo, PopupMenu.selected);
                }
                else if (op == 4) {
                    List<Integer> indexes = masSalidas(grafo);
                    panel.repaint(PopupMenu.rect);
                    for (var index : indexes) {
                        var city = grafo.getVertice(index);
                        var p = city.getPosition();
                        Lienzo.pintarCirculo(g, city.getNombre(), p.x, p.y, Color.red);
                    }
                    return;
                }

                if (dfsResult != null) {
                    for (var ciudad: dfsResult) {
                        var p = ciudad.getPosition();
                        Lienzo.pintarCirculo(g, ciudad.getNombre(), p.x, p.y, Color.red);
                    }
                    dfsResult = null;
                    panel.repaint(PopupMenu.rect);
                } else {
                    panel.repaint();
                }

                return;
            }

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
                    nombre = JOptionPane.showInputDialog("Nombre de la ciudad: ");
                    if (nombre == null) throw new Exception();
                    pais = JOptionPane.showInputDialog("Nombre del pais: ");
                    if (pais == null) throw new Exception();
                } catch (Exception e) {
                    return;
                }

                grafo.addVertice(new Ciudad(nombre, pais, new Vector2D(evt.getX(), evt.getY())));
                panel.repaint();
            }
            if (n == 2) {
                double costo, distancia;
                int tiempo;
                try {
                    costo = Double.parseDouble(JOptionPane.showInputDialog("Costo: "));
                    distancia = Double.parseDouble(JOptionPane.showInputDialog("Distancia: "));
                    tiempo = Integer.parseInt(JOptionPane.showInputDialog("Tiempo: "));
                } catch (Exception e) {
                    nodo1 = nodo2 = -1;
                    n = 0;
                    return;
                }
                grafo.addCosto(nodo1, nodo2, new Viaje(costo, distancia, tiempo));
                Lienzo.modified.add(nodo1);
                Lienzo.modified.add(nodo2);

                nodo1 = nodo2 = -1;
                n = 0;

                panel.repaint();
            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            var ci = Lienzo.hayCiudadEn(grafo, evt.getX(), evt.getY());

            if (ci == -1) return;

            PopupMenu.draw(evt.getX(), evt.getY(), g, ci);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        oldMousePosition.x = e.getX();
        oldMousePosition.y = e.getY();
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
            panel.repaint();
            return;
        }

        selected.setPosition(Lienzo.pos(e.getX(), e.getY()));
        panel.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        g = (Graphics2D) panel.getGraphics();
        super.mouseWheelMoved(e);
        var rot = e.getPreciseWheelRotation();
        Lienzo.setScale(-0.05 * rot);
        panel.repaint();
        for (int i = 0; i < grafo.orden(); i++) {
            Lienzo.modified.add(i);
        }
    }
}

class MyWindowListener extends WindowAdapter {
    ArchivoProyecto ar;
    private final Grafo<Ciudad, Viaje> grafo;

    public MyWindowListener(ArchivoProyecto archivoProyecto, Grafo<Ciudad, Viaje> grafo) {
        this.ar = archivoProyecto;
        this.grafo = grafo;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        try {
            ar.write(grafo);
        } catch (IOException e1) {
            System.out.println("no se guardo");
            e1.printStackTrace();
        }
    }
}