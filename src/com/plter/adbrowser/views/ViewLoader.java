package com.plter.adbrowser.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by plter on 2/17/16.
 */
public class ViewLoader {

    public static Result loadView(String viewName){
        FXMLLoader loader = new FXMLLoader(ViewLoader.class.getResource(viewName));
        try {
            Parent p = loader.load();
            return new Result(loader,p);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("View resource not found for name "+viewName);
        }
    }

    public static class Result {
        private FXMLLoader loader;
        private Parent view;

        public Result(FXMLLoader loader, Parent view) {
            this.loader = loader;
            this.view = view;
        }

        public FXMLLoader getLoader() {
            return loader;
        }

        public Parent getView() {
            return view;
        }

        public<T> T getController(){
            return getLoader().getController();
        }
    }
}
