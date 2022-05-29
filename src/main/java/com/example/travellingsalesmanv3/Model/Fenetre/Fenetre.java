package com.example.travellingsalesmanv3.Model.Fenetre;


public class Fenetre {
    //    private final int GAP = 50;
//    private final int ZOOM = 7;
//    private final ArrayList<itemColored> formes = new ArrayList();
//    private final JButton bouton = new JButton("Lancer");
//    private final JLabel label = new JLabel("valeur : ", SwingConstants.RIGHT);
//    private final JComboBox algos = new JComboBox(ListAlgo.values());
//    private final JComboBox voisins = new JComboBox(ListVoisins.values());
//    private final JComboBox fichier = new JComboBox(Tools.getAllFilesName().toArray());
//    Random rnd = new Random();
//    private Map currentMap = Tools.generateurSolutionAleatoire(ReadOneFile(getAllFilesName().get(1)), rnd);
//    private Algorithme algorithme;

    public Fenetre(String title) throws Exception {
//        super(title);
//        this.algorithme = ListAlgo.getAlgorithme(ListAlgo.values()[0], this, AlgosTE);
//        this.pack();
//        this.setSize(1200, 1100);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//        this.setResizable(false);
//        this.setUpComponent();
//        this.setUpRadioButtonsTE();
//
    }
//
//    private void setUpRadioButtonsTE() {
//        int yStart = 400;
//        Fenetre here = this;
//        for (ListVoisins lv : ListVoisins.values()) {
//            JRadioButton rb = new JRadioButton(lv.name());
//            rb.setBounds(1025, yStart, 150, 50);
//            yStart += 50;
//
//            rb.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    try {
//                        if (AlgosTE.contains(getAlgorithmeVoisin(lv)))
//                            AlgosTE.remove(getAlgorithmeVoisin(lv));
//                        else
//                            AlgosTE.add(getAlgorithmeVoisin(lv));
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            });
//            this.add(rb);
//        }
//    }
//
//    private void setUpComponent() {
//        this.algos.setVisible(true);
//        this.voisins.setVisible(true);
//        this.fichier.setVisible(true);
//
//        this.bouton.setBounds(1025, 50, 150, 50);
//        this.label.setBounds(500, 50, 100, 50);
//        this.algos.setBounds(1025, 200, 150, 50);
//        this.voisins.setBounds(1025, 400, 150, 50);
//        this.fichier.setBounds(1025, 300, 150, 50);
//        this.bouton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                clear();
//                currentMap = algorithme.lancer(currentMap);
//                System.out.println("test");
//                afficherMap(currentMap);
//            }
//        });
//        this.fichier.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    currentMap = generateurSolutionAleatoire(Tools.ReadOneFile((String) fichier.getSelectedItem()), rnd);
//                } catch (FileNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//        Fenetre here = this;
//        this.algos.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    algorithme = ListAlgo.getAlgorithme((ListAlgo) algos.getSelectedItem(), here, AlgosTE);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//        this.add(bouton);
//        this.add(algos);
//        this.add(fichier);
//        this.add(label);
//        this.validate();
//    }
//
//    private void ajouterPoint(Point p) {
//        Shape circleShape = new Ellipse2D.Double((p.x * ZOOM + GAP) - ZOOM / 2, (p.y * ZOOM + GAP) - ZOOM / 2, ZOOM, ZOOM);
//        formes.add(new itemColored(circleShape, Color.black));
//    }
//
//    private void ajouterDroite(Point p1, Point p2, Color color) {
//        Shape line = new Line2D.Double(p1.x * ZOOM + GAP, p1.y * ZOOM + GAP, p2.x * ZOOM + GAP, p2.y * ZOOM + GAP);
//        formes.add(new itemColored(line, color));
//    }
//
//    public void clear() {
//        formes.clear();
//    }
//
//    public void changerValeur(double valeur) {
//        this.label.setText("Valeur : " + valeur);
//        System.out.println("Valeur : " + valeur);
//    }
//
//    public void afficherMap(Map map) {
//        clear();
//        this.currentMap = map;
//        for (Vehicle v : map.getVehicles()) {
//            Random rand = new Random();
//            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
//            LinkedList<Client> listClient = v.getClientsToDeliver();
//            for (int i = 0; i < listClient.size() - 1; i++) {
//                ajouterDroite(listClient.get(i).getPoint(), listClient.get(i + 1).getPoint(), color);
//            }
//        }
//        for (Client c : map.getClients()) {
//            ajouterPoint(c.getPoint());
//        }
//        changerValeur(calculerDistanceTotal(map));
//        repaint();
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        Graphics2D g2D = (Graphics2D) g;
//        if (this.formes.size() > 1)
//            this.formes.get(this.formes.size() - 1).setColor(Color.red);
//        for (itemColored itemColored : this.formes) {
//            g2D.setColor(itemColored.getColor());
//            if (itemColored.getShape() instanceof Ellipse2D.Double) {
//                g2D.fill(itemColored.getShape());
//            } else {
//                g2D.draw(itemColored.getShape());
//            }
//        }
//    }
}