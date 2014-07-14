import org.scannotation.AnnotationDB
import org.scannotation.archiveiterator.FileIterator
import java.io.File
import javax.persistence.Entity
import javax.persistence.MappedSuperclass
import javax.persistence.Embeddable
import org.hibernate.cfg.Configuration
import org.hibernate.tool.hbm2ddl.SchemaExport
import org.hibernate.internal.util.ReflectHelper

def classLoader() {
    def classpathElements = project.compileClasspathElements + project.build.outputDirectory + project.build.testOutputDirectory
    URL[] urls = classpathElements.collect{new File(it).toURL()}.toArray()
    new URLClassLoader( urls, this.class.classLoader )
}

def scan(String... directories) {
    def annotationDb = new AnnotationDB()
    for (dir in directories) {
        log.info("Scanning ${dir}")
        annotationDb.scanArchives(new File(dir).toURL())
    }
    def index = annotationDb.annotationIndex
    // log.info("Entity: " + index.get(Entity.class.name))
    // log.info("MappedSuperclass: " + index.get(MappedSuperclass.class.name))
    // log.info("Embeddable: " + index.get(Embeddable.class.name))
    // def entityClasses = index.get(Entity.class.name) + index.get(MappedSuperclass.class.name) + index.get(Embeddable.class.name)
    def entityClasses = index.get(Entity.class.name)
    return entityClasses
}

def export(classes) {
    Configuration config = new Configuration();
    Properties properties = new Properties();
    // log.info("properties: ${project.properties}")
    def propertyfile = new FileInputStream(new File(project.basedir, project.properties.propertyfile))
    properties.load(propertyfile)
    propertyfile.close()

    config.setProperties(properties);
    for (c in classes) config.addAnnotatedClass(c)
    SchemaExport export = new SchemaExport(config);

    File file = new File(project.properties.outputfile)
    if (!file.absolute) {
        file = new File(project.build.directory, file)
    }
    if (!file.exists()) {
        file.parentFile.mkdirs();
    } else if (!file.isFile()) {
        fail("${file} is not a file")
    }

    log.info("exporting to ${file}")
    export.setOutputFile(file.absolutePath)
    export.setDelimiter(project.properties.delimiter ?: ";")
    export.setFormat(project.properties.format?.toBoolean() ?: false)

    export.execute(true, false, false, true)

    export.exceptions.each{log.error(it.toString())}
}

// The following line is a workaround to a bug in gmaven when trying to access project.properties inside a method fails
project.properties

def classNames = scan(project.build.outputDirectory);
def oldClassLoader = Thread.currentThread().contextClassLoader
Thread.currentThread().contextClassLoader = classLoader()
def classes = classNames.collect { it != null ? ReflectHelper.classForName(it, this.class) : null;}
export(classes)
Thread.currentThread().contextClassLoader = oldClassLoader
