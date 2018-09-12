package mx.babel.arq.comun.utils;

public class VariablePlantillaUtils {

	private String valor;
	private String fontFamily;
	private int fontSize;
	private boolean bold;
	
	public VariablePlantillaUtils() {
		super();
	}
	
	public VariablePlantillaUtils(String valor) {
		super();
		this.valor = valor;
	}
	
	public VariablePlantillaUtils(String valor, String fontFamily, int fontSize, boolean bold) {
		super();
		this.valor = valor;
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
		this.bold = bold;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}
	
	
	
}
