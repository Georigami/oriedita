package oriedita.editor.service.impl;

import oriedita.editor.databinding.CanvasModel;
import oriedita.editor.databinding.FoldedFigureModel;
import oriedita.editor.databinding.FoldedFiguresList;
import oriedita.editor.service.FileSaveService;
import oriedita.editor.service.FoldingService;
import oriedita.editor.service.TaskExecutorService;
import oriedita.editor.service.TaskService;
import oriedita.editor.task.FoldingEstimateSave100Task;
import oriedita.editor.task.FoldingEstimateSpecificTask;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class TaskServiceImpl implements TaskService {
    private final TaskExecutorService foldingExecutor;
    private final CanvasModel canvasModel;
    private final FileSaveService fileSaveService;
    private final FoldingService foldingService;
    private final FoldedFiguresList foldedFiguresList;
    private final FoldedFigureModel foldedFigureModel;

    @Inject
    public TaskServiceImpl(
            @Named("foldingExecutor") TaskExecutorService foldingExecutor,
            CanvasModel canvasModel,
            FileSaveService fileSaveService,
            FoldingService foldingService,
            FoldedFiguresList foldedFiguresList,
            FoldedFigureModel foldedFigureModel
    ) {

        this.foldingExecutor = foldingExecutor;
        this.canvasModel = canvasModel;
        this.fileSaveService = fileSaveService;
        this.foldingService = foldingService;
        this.foldedFiguresList = foldedFiguresList;
        this.foldedFigureModel = foldedFigureModel;
    }

    public void executeFoldingEstimateSave100Task() {
        foldingExecutor.executeTask(new FoldingEstimateSave100Task(canvasModel, foldingService, fileSaveService, foldedFiguresList));
    }

    public void executeFoldingEstimateSpecificTask() {
        foldingExecutor.executeTask(new FoldingEstimateSpecificTask(foldedFigureModel, foldingService, canvasModel, foldedFiguresList));
    }
}
