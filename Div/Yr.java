import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

class Yr {
	public static void main(String[] args) throws IOException{
		String search = JOptionPane.showInputDialog(null,"Skriv inn sted");
		Document page = Jsoup.connect("https://www.yr.no/soek/soek.aspx?sted="+search).get();
		Elements result = page.select("table.yr-table-search-results");
		String link = result.select("a").first().attr("href");
		Document doc = Jsoup.connect("https://www.yr.no"+link).get();
		Elements items = doc.select("table.yr-table-overview2");
		for (Element i : items){
			System.out.println(i.select("caption").text());
			Elements data = i.select("tbody").select("tr");
			for (Element k : data){
				String textUt = "";
				int c = 0;
				for (Element f : k.children()){
					if (c==0){
						String[] tid = f.text().split("–");
						textUt += "\t" + (tid[0]+" - "+tid[1]+":\t");
					} else {
						if (c>2){
							if (f.text().split("–").length>1){
								textUt += "Nedbor: "+f.text().split("–")[0]+" - "+f.text().split("–")[1]+"\n\t\t";
							} else {
								textUt += f.text()+"\n\t\t";
							}
						} else {
							textUt += f.text()+"\n\t\t";
						}
					}
					c++;
				}
				textUt = textUt.replaceAll("°"," grader celcius");
				System.out.println(textUt);
			}
			System.out.println();
		}
	}
}