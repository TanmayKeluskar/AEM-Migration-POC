import groovy.xml.slurpersupport.GPathResult
import groovy.xml.MarkupBuilder

void renderPage(Object pageData, GPathResult inXml, MarkupBuilder outXml, Map replacements){

    GroovyShell shell = new GroovyShell()
    def commons = shell.parse(new File('templates/.commons.groovy').text)
    def pageProperties = commons.pageProperties(pageData, inXml, '/conf/wknd-spa-react/settings/wcm/templates/spa-page-template','wknd-spa-react/components/page', replacements)
    
    outXml.'jcr:root'(commons.rootProperties()) {
        'jcr:content'(pageProperties) {
            'root'(commons.component('wcm/foundation/components/responsivegrid')){
                'responsivegrid'(commons.component('wcm/foundation/components/responsivegrid')){
                    'title'(commons.component('wknd-spa-react/components/title', ['fontSize': 'h1', 'header': inXml.metadata.title.toString()]))
                    'text'(commons.component('wknd-spa-react/components/text', ['textIsRich': true, 'text': inXml.content.toString()]))
                }
            }
        }
    }
}