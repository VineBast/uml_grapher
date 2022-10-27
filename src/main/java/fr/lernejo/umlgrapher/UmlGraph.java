package fr.lernejo.umlgrapher;

import java.lang.reflect.Modifier;

public class UmlGraph {
    private final Class[] classes;

    public UmlGraph(Class[] classes) {
        this.classes = classes;
    }

    public String as(GraphType graphType) {
        String resulta = "";
        if (graphType == GraphType.Mermaid) {
            try {
                InternalGraphRepresentation graph = new InternalGraphRepresentation(classes);
                resulta = new MermaidFormatter().format(graph);
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getClass() + " - " + e.getMessage());
            }
        }
        return resulta;
    }

    private String getMermaidSyntax() {
        String syntax = "classDiagram\n";
        for (Class classe : this.classes) {
            syntax += "class " + classe.getSimpleName() + " {\n";
            if (Modifier.isInterface(classe.getModifiers())) {
                syntax +="    <<interface>>\n";
            }
            syntax += "}\n";
        }
        return syntax;
    }
}
