public class Planet{
    public static double G = 6.67e-11;

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	public double calcDistance(Planet planet){
		double distance ;
		double dx = xxPos - planet.xxPos;
		double dy = yyPos - planet.yyPos;
		distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}
    public double calcForceExertedBy(Planet planet){
        double distance = this.calcDistance(planet);
        return Planet.G * mass * planet.mass /(distance * distance);
    }
    public double calcForceExertedByX(Planet planet){
        return  this.calcForceExertedBy(planet) * (planet.xxPos - xxPos) / this.calcDistance(planet); 
    }
    public double calcForceExertedByY(Planet planet){
        return  this.calcForceExertedBy(planet) * (planet.yyPos - yyPos) / this.calcDistance(planet); 
    }
    public double calcNetForceExertedByX(Planet[] planets){
        double NetForceExertedByX = 0;
        for(int i = 0; i < planets.length;i += 1){
            if(this.equals(planets[i])){
                continue;
            }
            NetForceExertedByX += this.calcForceExertedByX(planets[i]);
        }
        return NetForceExertedByX;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        double NetForceExertedByY = 0;
        for(int i = 0; i < planets.length;i += 1){
            if(this.equals(planets[i])){
                continue;
            }
            NetForceExertedByY += this.calcForceExertedByY(planets[i]);
        }
        return NetForceExertedByY;
    }
    public void update(double time, double x_force, double y_force){
        double x_a = x_force / mass;
        double y_a = y_force / mass;
        xxVel += x_a * time;
        yyVel += y_a * time;
        xxPos += xxVel * time;
        yyPos += yyVel * time; 
    }
}