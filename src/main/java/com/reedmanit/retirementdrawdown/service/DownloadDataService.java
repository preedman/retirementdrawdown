package com.reedmanit.retirementdrawdown.service;

import com.reedmanit.retirementdrawdown.model.AnnualDrawdown;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class DownloadDataService {

    private PrintWriter out;
    private File downloadFile;

    private List<AnnualDrawdown> annualDrawdownList;

    public DownloadDataService(List<AnnualDrawdown> listOfDrawDowns) throws FileNotFoundException {

        downloadFile = new File("drawdown.csv");

        out = new PrintWriter(downloadFile);
        this.annualDrawdownList = listOfDrawDowns;


    }

    public void download() {
        out.println("year,open balance,withdrawal,income,close balance");

        Iterator<AnnualDrawdown> iterator = annualDrawdownList.iterator();
        while (iterator.hasNext()) {
            AnnualDrawdown annualDrawdown = iterator.next();
            out.println("%s,%s,%s,%s,%s".formatted(
                    annualDrawdown.getYear(),
                    annualDrawdown.getOpeningBalance(),
                    annualDrawdown.getWithdrawal(),
                    annualDrawdown.getIncome(),
                    annualDrawdown.getClosingBalance()
            ));
        }
        out.close();
    }

    public void downloadData() throws FileNotFoundException {

        Stream<AnnualDrawdown> drawdownStream = null;

        drawdownStream = this.annualDrawdownList.stream();

        out.println("year,open balance,withdrawal,income,close balance");

        drawdownStream.forEach(annualDrawdown -> {
            out.println("%s,%s,%s".formatted(
                annualDrawdown.getYear(),
                annualDrawdown.getOpeningBalance(),
                annualDrawdown.getWithdrawal(),
                annualDrawdown.getIncome(),
                annualDrawdown.getClosingBalance()
            ));
        });
        out.close();
    }


}
