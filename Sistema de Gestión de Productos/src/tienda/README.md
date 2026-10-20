Como se actualiza el ComboBox cuando se registra una nueva categoria

Las categorías se guardan en la clase Datos, dentro de una ObservableList<Categoria>. Es una interfaz de la biblioteca JavaFX que funciona como una lista común (similar a ArrayList), pero con una característica clave: notifica automáticamente a los componentes de la interfaz gráfica cuando su contenido cambia.

PrincipalController le asigna esa lista al ComboBox con la intrunccion categoria.setItems(Datos.categorias());
eso hace que el ComBox no haga una copia sino que muestra la lista que esta en Datos