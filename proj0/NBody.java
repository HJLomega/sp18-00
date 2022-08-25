public class NBody{
    public static double readRadius(String file_name){
        In in = new In(file_name);
        int leave_out = in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String file_name){
        In in = new In(file_name);
        int N = in.readInt();
        double leave_out = in.readDouble();
        Planet[] planets = new Planet[N];
        for(int i = 0; i < N; i += 1){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return planets;
    }
    public static void main(String[] args){
        /* Collecting All Needed Input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = NBody.readPlanets(filename);
        double radius = NBody.readRadius(filename);
        /* Drawing the Background */
        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(Planet planet:planets){
            planet.draw();
        }
        /* Creating an Animation */
        StdDraw.enableDoubleBuffering(); 
        for(double time = 0; time < T; time += dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0;i < planets.length; i += 1){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0;i < planets.length; i += 1){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet planet:planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        /* Printing the Universe */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
} 