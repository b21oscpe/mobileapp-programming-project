
# Rapport for projektuppgift

### RecyclerView
För uppgiften har det skapats en recyclerview där varje item har en onclick-listener, där en detaljvy presenteras med ytterligare data om vilka länder varje "river" går igenom.
```
public RiverViewHolder(final View itemView) {
            super(itemView);
            river = itemView.findViewById(R.id.river);
            location = itemView.findViewById(R.id.location);
            size = itemView.findViewById(R.id.size);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    River river = rivers.get(getAdapterPosition());
                    Log.d("==>", String.format("Navigated to %s", river.getID()));
                    Intent intent = new Intent(context, DetailedActivity.class);
                    intent.putExtra("name", river.getName());
                    intent.putExtra("location", river.getLocation());
                    intent.putExtra("size", river.getSize().toString());
                    intent.putExtra("aux", river.getAuxdata());
                    context.startActivity(intent);
                }
            });
        }
```
För att recyclerview skulle fungera behövdes en dataklass:
```
public River(String ID, String name, String location, Integer size, String auxdata){
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.size = size;
        this.auxdata = auxdata;
    }
```

### Filter (FilterSearch, FilterAlphabetical, ...)
Filter interfacet skapades för att skapa flera olika filter som kan appliceras på listan med "rivers". Klasser såsom FilterSearch implementerar interfacet. 
Filtret appliceras i ```RecyclerViewAdapter.java``` på ```allRivers``` listan. Valet av filtrering görs med knappar/textfält i ```MainActivity.java```.
```
public interface Filter {
    ArrayList<River> apply(ArrayList<River> items, String query);
}
```
För att kunna spara filtret har jag använt mig av SharedPreferences där en string bestämmer vilket filter som ska appliceras. Alla andra strings än de cases som är bestämda tolkas som en search.
```
SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        String filter = preferences.getString("filter", "none");
        Log.d("==>", filter);
        switch (filter) {
            case "ascending":
                filterAscending();
            case "descending":
                filterDescending();
            case "alphabetical":
                filterAlphabetical();
            case "none":
                break;
            default:
                filterSearch(filter);
        }
```
Home
![home](https://github.com/b21oscpe/mobileapp-programming-project/assets/102578165/83ac010b-5f31-4ca7-854e-c0150662ce5a)

Search
![search](https://github.com/b21oscpe/mobileapp-programming-project/assets/102578165/99cbfcad-c994-4777-9724-4ebcaa2460dc)

Sort ascending
![sort_asc](https://github.com/b21oscpe/mobileapp-programming-project/assets/102578165/9d608ee2-449f-455a-9943-e9707a295807)

About
![about](https://github.com/b21oscpe/mobileapp-programming-project/assets/102578165/fdf0b78e-bdd4-4f83-8bf3-08600a0737dd)

Detailed
![detailed](https://github.com/b21oscpe/mobileapp-programming-project/assets/102578165/2d9c3d60-900c-4088-bac3-dc732e1cfe34)

Preferences
[filter_saved.webm](https://github.com/b21oscpe/mobileapp-programming-project/assets/102578165/a31746b7-0bd9-4b2e-976d-54c69480ec4f)







