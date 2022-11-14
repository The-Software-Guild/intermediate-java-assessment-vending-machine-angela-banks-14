package com.ab.vendingmachine.dao;

import com.ab.vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public class FileDaoImpl implements VendingMachineDao{

    private Map<String, Item> items = new HashMap<>();
    private final String ITEM_FILE;
    public static final String DELIMITER = "::";

    public FileDaoImpl() {
        ITEM_FILE = "items.txt";
    }

    //constructor for test files
    public FileDaoImpl(String testFile) {
        ITEM_FILE = testFile;
    }

    @Override
    public int getItemInventory(String name) throws VendingMachineException {
        readFile();
        return items.get(name).getNumInventoryItems();
    }

    @Override
    public void removeItem(String name) throws VendingMachineException {
        readFile();
        int prevItem = items.get(name).getNumInventoryItems();
        items.get(name).setNumInventoryItems(prevItem - 1);
        writeFile();
    }

    @Override
    public Item getItem(String name) throws VendingMachineException {
        readFile();
        return items.get(name);
    }

    @Override
    public Map<String, BigDecimal> getItemNamesInStock() throws VendingMachineException {
        readFile();
        Map<String, BigDecimal> itemsInStock = items.entrySet()
                .stream()
                .filter(map -> map.getValue().getNumInventoryItems() > 0)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().getCost()));

        return itemsInStock;
    }

    public String marshallItem(Item item) {
        String itemAsText = item.getName() + DELIMITER;
        itemAsText += item.getCost() + DELIMITER;
        itemAsText += item.getNumInventoryItems();
        return itemAsText;
    }

    private Item unmarshallItem(String itemAsText) {
        //implement
        String [] itemTokens = itemAsText.split("::");
        String name = itemTokens[0];
        Item itemFromFile = new Item(name);
        BigDecimal bigDecimal = new BigDecimal(itemTokens[1]);
        itemFromFile.setCost(bigDecimal);
        itemFromFile.setNumInventoryItems(Integer.parseInt(itemTokens[2]));
        return itemFromFile;
        }

    public void readFile() throws VendingMachineException {
        Scanner scan;
        try {
            //implement
            scan = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineException("File not found", e);
        }
        String currentLine;
        Item currentItem;

        while (scan.hasNextLine()) {
            currentLine = scan.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scan.close();
    }

        @Override
        public List<Item> listAllItems() throws VendingMachineException {
            readFile();
            return new ArrayList(items.values());
        }

        private void writeFile() throws VendingMachineException{
            PrintWriter out;
            try {
                //implement
                out = new PrintWriter(new FileWriter(ITEM_FILE));
            } catch (IOException e) {
                throw new VendingMachineException("Could not save item data", e);
            }
            String itemAsText;
            List <Item> itemList = this.listAllItems();
            for (Item currentItem : itemList) {
                itemAsText = marshallItem(currentItem);
                out.println(itemAsText);
                out.flush();
            }
            out.close();
        }
    }

