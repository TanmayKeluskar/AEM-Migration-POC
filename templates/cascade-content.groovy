import groovy.xml.slurpersupport.GPathResult
import groovy.xml.MarkupBuilder

void renderPage(Object pageData, GPathResult inXml, MarkupBuilder outXml, Map replacements) {
    GroovyShell shell = new GroovyShell()
    def commons = shell.parse(new File('templates/.commons.groovy').text)
    def pageProperties = commons.pageProperties(pageData, inXml, '/conf/wknd-spa-react/settings/wcm/templates/cascade-demo0', 'weretail/components/structure/page', replacements)

    outXml.'jcr:root'(commons.rootProperties()) {
        'jcr:content'(pageProperties) {
            'root'(commons.component('wcm/foundation/components/responsivegrid')) {
                'contentpagecomponent'(commons.component('wknd-spa-react/components/contentpagecomponent', 
                ['logoImg': '/content/dam/wknd-spa-react/cascade/btlogo.gif', 
                'logoText': inXml.metadata.title.toString(),
                'pageTitle': inXml.pageTitle.toString(),
                'pageSubTitle': inXml.pageSubTitle.toString(),
                'pageData': inXml.pageData.toString()]))
            }
        }
    }
}
