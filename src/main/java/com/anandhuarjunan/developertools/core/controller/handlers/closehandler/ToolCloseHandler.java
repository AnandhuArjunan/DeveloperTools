package com.anandhuarjunan.developertools.core.controller.handlers.closehandler;

import java.util.ArrayList;
import java.util.List;

import com.anandhuarjunan.developertools.core.utils.DialogBox;
import com.anandhuarjunan.developertools.core.views.ClosableTool;
import com.anandhuarjunan.developertools.core.views.FxmlLoaderTool;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ToolCloseHandler implements Handler {

	private TabPane tabPane = null;
	private Event event = null;
	private List<Tab> pendingToClose = new ArrayList<>();
	private StringBuilder stringBuilder = new StringBuilder();

	public ToolCloseHandler(TabPane tabPane, Event event) {
		this.tabPane = tabPane;
		this.event = event;
	}

	@Override
	public void handle() {
		ObservableList<Tab> runningTabs = tabPane.getTabs();

		if (null != runningTabs && !runningTabs.isEmpty()) {
			for (Tab tab : runningTabs) {
				Object tabImpl = tab.getUserData();
				if (null != tabImpl) {
					if (tabImpl instanceof FxmlLoaderTool fxmlTool) {
						if (fxmlTool.getController()instanceof ClosableTool closableTool) {
							close(closableTool, tab);
						}

					} else if (tabImpl instanceof ClosableTool closableTool) {
						close(closableTool, tab);
					}
				}
			}
		}

		handleRemaining();
	}

	private void handleRemaining() {
		if (!pendingToClose.isEmpty()) {

			boolean canItBeClosed = DialogBox.confirmationDialog("Do you want to close ?",
					"Below task(s) are still running.", stringBuilder.toString());
			if (canItBeClosed) {
				for (Tab tab : pendingToClose) {
					Object tabImpl = tab.getUserData();
					if (null != tabImpl) {
						if (tabImpl instanceof FxmlLoaderTool fxmlTool) {
							if (fxmlTool.getController() instanceof ClosableTool closableTool) {
								closableTool.close();
							}
						} else if (tabImpl instanceof ClosableTool closableTool) {
							closableTool.close();
						}
					}

				}
			} else {
				event.consume();
			}
		}
	}

	public void close(ClosableTool closableTool, Tab tab) {
		boolean canBeClosed = closableTool.canBeClosed();
		if (canBeClosed) {
			closableTool.close();
		} else {
			pendingToClose.add(tab);
			stringBuilder.append(tab.getText() + "(" + tab.getId() + ")" + "\n");
		}
	}

}
