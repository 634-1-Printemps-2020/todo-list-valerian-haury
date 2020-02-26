import exceptions.TaskException;
import models.Resolution;
import models.Status;
import models.Task;
import org.junit.Assert;
import org.junit.Test;


import java.time.LocalDate;

public class TaskTest {

    @Test
    public void execute() throws TaskException {
        Task task = new Task("Provencal.leGaulois", "Trouver le Graal", LocalDate.of(2050,8,16));
        Assert.assertEquals(task.getStatus(), Status.OPEN);
        Assert.assertEquals(task.getResolution(), Resolution.WAITING);


        task.execute();
        Assert.assertEquals(task.getStatus(), Status.CLOSED);
        Assert.assertEquals(task.getResolution(), Resolution.DONE);
        Assert.assertEquals(LocalDate.now(), task.getExecutionDate());
    }

    @Test (expected = TaskException.class)
    public void report() throws TaskException {
        Task task = new Task("Karadoc", "Casse-dalle de 4 heure du matin", LocalDate.of(2021,1,16));
        task.report(task.getExecutionDate().minusYears(1));
    }

    @Test
    public void reportOK() throws TaskException {
        Task task = new Task("Merlin", "Concevoir une potion utile", LocalDate.of(2020,7,7));
        task.report(task.getExecutionDate().plusYears(10));
        Assert.assertEquals(LocalDate.of(2030,7,7),task.getExecutionDate());
    }

    @Test
    public void cancel() throws TaskException {
        Task task = new Task("Bohort", "Partir se battre sur le champs de bataille", LocalDate.of(2020,7,7));
        task.cancel();
        Assert.assertEquals(task.getStatus(), Status.CANCELED);
        Assert.assertEquals(task.getResolution(), Resolution.IGNORED);
    }
}
