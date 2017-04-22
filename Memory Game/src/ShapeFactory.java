/**
 * Shape Factory class that performs the factory design pattern.
 * @author Lam Ngo
 */
public class ShapeFactory {
	/**
	 * Create a shape upon request
	 * @param type
	 * @param shapeType
	 * @param filling
	 * @param color
	 * @return shape with type, shape type, filling and color
	 */
	public Shape createShape(String type, int shapeType, int filling, int color) {
		Shape shape = null;
			if (type.equals("SetShape")){
				if (shapeType == 0){
					shape = new Bean(0,0,color,filling,shapeType);
				}else if(shapeType == 1){
					shape = new Diamond(0,0,color,filling,shapeType);
				}else if(shapeType == 2){
					shape = new Oval(0,0,color,filling,shapeType);
				}else if(shapeType == 3){
					shape = new James(0,0,color,filling,shapeType);
				}else if(shapeType == 4){
					shape = new Varun(0,0,color,filling,shapeType);
				}else if(shapeType == 5){
					shape = new Diego(0,0,color,filling,shapeType);
				}
			}else if (type.equals("Outline")){
					shape = new Rectangle(0,0,0,0, 6);
			}
		return shape;
	}
}
