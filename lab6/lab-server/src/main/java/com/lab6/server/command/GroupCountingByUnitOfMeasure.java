package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.product.Product;
import com.lab6.server.product.UnitOfMeasure;

public class GroupCountingByUnitOfMeasure extends CollectionCommand {

    public GroupCountingByUnitOfMeasure(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute() {
        int meters = 0;
        int centimeters = 0;
        int pcs = 0;
        int grams = 0;
        int milligrams = 0;
        for (Product product : this.getCollectionManager().getProducts()) {
             if(product.getUnitOfMeasure() == UnitOfMeasure.METERS)
                    meters++;
            else if(product.getUnitOfMeasure() == UnitOfMeasure.CENTIMETERS)
                    centimeters++;
            else if(product.getUnitOfMeasure() == UnitOfMeasure.PCS)
                    pcs++;
            else if(product.getUnitOfMeasure() == UnitOfMeasure.GRAMS)
                    grams++;
            else if(product.getUnitOfMeasure() == UnitOfMeasure.MILLIGRAMS)
                    milligrams++;

        }
        String text = "Группа по единицам измерения:\nМетры: " + meters + "\nСантиметры: " + centimeters + "\nШтуки: " + pcs + "\nГраммы: " + grams + "\nМиллиграммы: " + milligrams + "\n";
        return new  Response(text, null);
    }

    @Override
    public void setArg(String arg) {

    }
}
