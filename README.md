
# Dokumentation
Dokumention för projektuppgiften i kursen. 

## Skiss / Design
Detta projekt byggde vidare på en relativt simpel design. En tydlig länk eller knapp till "About"-skärmen tillsammans med en vy över knappar för att lägga till filter vilket är del av VG-kraven. Tack vare tidsbrist på grund av andra kurser, implementerades ej denna funktionalitet men designen är fortfarande kvar. Under filtersektionen ska informationen från JSON-tjänsten visas (vänster i bilden). Denna andra aktivitet som ska innehålla information kring målgruppen är också simpel. En rubrik och lite text som beskriver målgruppen (höger i bilden). 

![Skiss](https://github.com/a20gabpa/mobileapp-programming-project/assets/102604680/07d7868f-7829-4ef6-a9f8-cc5d6a1eb290)

## Webbtjänst - JSON
För denna fiktiva målgrupp, användes JSON för att representera diverse objekt i ett inventariesystem för ett företag där flera olika attribut kan behöva lagras. _ID_ och _login (även kallat type)_ har redan ett självförklarande namn. 

_Name (textsträng)_ - är namnet på själva objektet i inventariet.  
_Company (textsträng)_ - är namnet på det företag som objektet kommer ifrån.
_Location (textsträng)_ - är den plats detta föremål befinner sig, i detta fiktiva exempel handlar det om skåp eller rum.
_Category (textsträng)_ - är vilken kategori detta objekt tillhör. Exempelvis "Tools" eller "Unknown"
_Size (heltal)_ - ett nummer som representerar storleken på objektet. Detta används även som en värde för att beskriva hur otympligt ett objekt är.
_Cost (heltal)_ - ett nummer som beskriver kostnaden eller uppskattad värde på objektet. 
_AuxData (textsträng)_ - detta attribut används för att lagra eventuella kommentarer angående objektet. 

```
{
    "ID":"a20gabpa_1",
    "name":"Picky Toolsmith",
    "type":"a20gabpa",
    "company":"Tools & Sons",
    "location":"Locker 17",
    "category":"Tools",
    "size":50,
    "cost":70,
    "auxdata":"Heavy"
}
```

## Implementationsexempel
Den första implementeringsdetaljen handlar om att via den givna klassen _JsonTask.java_ hämta min skapade JSON-data från Lenasys och visa det på skärmen. För att hämta datan användes en URL som länkade till en API som med hjälp av mitt användarnamn gav mig tillgång till ett JSON-objekt. Detta objekt hämtades med hjälp av en funktion från _JsonTask.java_ som asynkroniskt hämtar informationen från internet (Kodsnutt 1). URL för detta sparades även i en variabel för att underlätta överblick över koden. Länk till commit där jag får _JsonTask.java_ att fungera: https://github.com/a20gabpa/mobileapp-programming-project/commit/841de16769a60a16b5506fac1c867ef2d6987c01

Efter att informationen hade laddats ner, behövdes informationen extraheras och sparas i en lista med objekt. Detta i sin tur gjordes med hjälp av _Gson_ vilket efter att ha skapat en _TypeToken_ över objekten. För att kunna representera detta användes en klass _InventoryItem.java_ vilket innehåller flera variabler för att representera denna data (Kodsnutt 2).

*Kodsnutt 1: MainActivity.java*
```
    ...
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a20gabpa";
    ...
    new JsonTask(this).execute(JSON_URL);
    ...
    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<InventoryItem>>() {}.getType();
        items = gson.fromJson(json, type);

        adapter = new ViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    ...
```

*Kodsnutt 2: InventoryItem.java*
```
    /* ================== VARIABLES ================== */
        private String ID, name, type, company;
        private int size, cost;
        private String location, category, auxdata;
    /* =============================================== */
    ...
    public String GetBasicString() {
        return "ID: " + this.ID +
                ", Name: " + this.name +
                ",\n Company: " + this.company +
                ", Cost: " + this.cost +
                ", Login: " + this.type + "\n";
    }

```

Nästa steg var därefter att med hjälp av en adapter, hämta information från listan, formatera den till en textsträng och visa den i _RecyclerView_. Notera att även i kodsnutt 2 visas även en funktion som formaterar en textsträng av all information i klassen med hjälp av konkatenering. En ny klass med namnet _ViewAdapter.java_ som baseras på _(eng. extends)_ klassen _RecyclerView.Adapter<ViewAdapter.ViewHolder>_ används för att visa informationen på skärmen. Detta klassen innehåller en lokal lista med alla objekt som klassen får när den skapas via en konstruktor (Kodsnutt 3) och implementerar även de nödvändiga funktionerna som behövs för att en adapter ska fungera. 

*Kodsnutt 3: ViewAdapter.java*
```
    ...
    private List<InventoryItem> items;
    ...
    public ViewAdapter(Context context, List<InventoryItem> inventoryItems) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = inventoryItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.text_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryItem item = items.get(position);
        holder.textView.setText(item.GetBasicString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
```

I kodsnutt 4 skapas en klass som bygger vidare på _ViewHolder_ från _RecyclerView_ och används för att visa ett objekt samt dess metadata i en _RecyclerView_. Detta klass har även implementerat funktionalitet för att kunna hämta _TextView_.

*Kodsnutt 4: ViewAdapter.java*
```
    ...
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView);
        }
        public TextView getTextView() {
            return textView;
        }
    }
    ...
```

Slutligen i kodsnutt 5 visas _layouten_ för varje objekt som finns _RecyclerView_ vilket består av en simpel _TextView_. Resultatet av detta gör att man kan se all data på huvudskärmen enligt figur 1. 
Länk till commit där denna funktionalitet implementeras: https://github.com/a20gabpa/mobileapp-programming-project/commit/20109487fe948cb5aa06fe7ef5caee4f69a4583d 

*Kodsnutt 5: text_row_item.xml*
```
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        tools:visibility="visible">
    
        <TextView
            android:id="@+id/textView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="10dp"
            android:layout_marginEnd="10dp"
            android:text="TextView"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            tools:layout_editor_absoluteY="-1dp"
            tools:visibility="visible" />
    </androidx.constraintlayout.widget.ConstraintLayout>
```
*Figur 1: Startskärm / Huvudskärm*
![Screenshot_20240526_194008](https://github.com/a20gabpa/mobileapp-programming-project/assets/102604680/fd7a599d-c5f2-4e9d-8652-7fb20e0d61ec)

![Screenshot_20240526_194016](https://github.com/a20gabpa/mobileapp-programming-project/assets/102604680/97a032a3-5dde-41e9-9f9b-38a38feac6be)

Slutligen, tanken var att implementera filter-funktionalitet för att kunna uppfylla kraven för VG men detta implementerades aldrig i slutändan vilket ledde till att det finns fortfarande knappar och textfält för detta men gör inget just nu. 

## Reflektion
Jag upplevde att nivån på projektet var OK. Anser att de tidigare inlämningar har förberett en för att kunna göra denna uppgift då merparten av implementationen kundes inspireras av tidigare inlämningar. Jag hade några problem under utveckling som var relaterad till JSON och RecyclerView men efter lite väl många timmar kunde problemet lösas. Jag skulle uppskatta en tydligare genomgång på JSON och hur adaptrar kopplas ihop med _RecyclerView_.
