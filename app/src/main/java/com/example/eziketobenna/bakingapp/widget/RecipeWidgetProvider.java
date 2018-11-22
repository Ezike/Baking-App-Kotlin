package com.example.eziketobenna.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.ui.details.StepListActivity;
import com.example.eziketobenna.bakingapp.ui.recipes.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

        //Get the id and name of the last chosen recipe from preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(StepListActivity.PREF, Context.MODE_PRIVATE);
        int recipeId = sharedPreferences.getInt(StepListActivity.RECIPE_ID, 0);
        String defaultValue = "No Recipe";
        CharSequence recipeName = sharedPreferences.getString(StepListActivity.RECIPE_NAME, defaultValue);
        views.setTextViewText(R.id.appwidget_text, recipeName);

        //Set adapter
        Intent intent = new Intent(context, ListWidgetService.class);
        views.setRemoteAdapter(R.id.appwidget_list, intent);
        views.setEmptyView(R.id.appwidget_list, R.id.appwidget_empty);

        //open mainActivity when title is clicked
        Intent clickIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickIntent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.appwidget_list);
    }

    public static void updateWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecipeWidgetProvider.class));
        // update list view with data
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_list);
        //Now update all widgets
        for (int appWidgetId : appWidgetIds) {
            RecipeWidgetProvider.updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        SharedPreferences sharedPreferences = context.getSharedPreferences(StepListActivity.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(StepListActivity.RECIPE_NAME);
        editor.remove(StepListActivity.RECIPE_ID);
        editor.apply();
    }
}

