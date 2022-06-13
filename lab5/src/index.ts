import {
    BackSide,
    Color,
    DirectionalLight,
    Fog,
    HemisphereLight,
    Mesh,
    MeshLambertMaterial,
    PerspectiveCamera,
    PlaneGeometry,
    Scene,
    ShaderMaterial,
    SphereGeometry,
    sRGBEncoding,
    WebGLRenderer,
    TextureLoader,
    AxesHelper,
    Group,
    PCFSoftShadowMap,
    CameraHelper,
} from 'three';
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls';
import {OBJLoader} from "three/examples/jsm/loaders/OBJLoader";
import {MTLLoader} from "three/examples/jsm/loaders/MTLLoader";

// Set up renderer
const container = document.getElementById('container') as Element;
const renderer = new WebGLRenderer({antialias: true});
renderer.setPixelRatio(window.devicePixelRatio);
renderer.setSize(window.innerWidth, window.innerHeight);
container.appendChild(renderer.domElement);
renderer.outputEncoding = sRGBEncoding;
renderer.shadowMap.enabled = true;
renderer.shadowMap.type = PCFSoftShadowMap;

// Set up camera
const camera = new PerspectiveCamera(30, window.innerWidth / window.innerHeight, 1, 5000);
const orbitControls = new OrbitControls(camera, renderer.domElement);
camera.position.set(250, 250, 500);
camera.lookAt(0, 70, 0);
orbitControls.update();

// Set up scene
const scene = new Scene();
scene.background = new Color(0x030303);
scene.fog = new Fog(scene.background, 1, 5000);

// Lights
const hemisphereLight = new HemisphereLight(0xffffff, 0xffffff, 0.6);
hemisphereLight.color.setHSL(0.6, 1, 0.6);
hemisphereLight.groundColor.setHSL(0.095, 1, 0.75);
hemisphereLight.position.set(0, 50, 0);
scene.add(hemisphereLight);

const directionalLight = new DirectionalLight(0xffffff, 1);
directionalLight.color.setHSL(0.1, 1, 0.95);
directionalLight.position.set(-1, 1.75, 1);
directionalLight.position.multiplyScalar(70);
scene.add(directionalLight);

directionalLight.castShadow = true;

const d = 100;
directionalLight.shadow.camera.left = -d;
directionalLight.shadow.camera.right = d;
directionalLight.shadow.camera.top = d;
directionalLight.shadow.camera.bottom = -d;
directionalLight.shadow.camera.visible = true;

// Ground
const groundGeometry = new PlaneGeometry(10000, 10000);
const groundMaterial = new MeshLambertMaterial({color: 0xffffff});
groundMaterial.color.setHSL(0.095, 1, 0.75);

const ground = new Mesh(groundGeometry, groundMaterial);
ground.rotation.x = -Math.PI / 2;
ground.receiveShadow = true;

scene.add(ground);

// Sky
const uniforms = {
    topColor: {value: new Color(0x0077ff)},
    bottomColor: {value: new Color(0xffffff)},
    offset: {value: 33},
    exponent: {value: 0.6}
};

uniforms.topColor.value.copy(hemisphereLight.color);
scene.fog.color.copy(uniforms.bottomColor.value);

const sky = new Mesh(
    new SphereGeometry(4000, 32, 15),
    new ShaderMaterial({
        uniforms,
        vertexShader: document.getElementById('vertexShader')!.textContent as string,
        fragmentShader: document.getElementById('fragmentShader')!.textContent as string,
        side: BackSide,
    })
);
sky.castShadow = true;

scene.add(sky);

// Models
let propellerObj: Group;
let helicopterObj: Group;
let tailPropellerObj: Group;

const loader = new MTLLoader();
loader.load('assets/models/AW101/propeller.mtl', (propellerMaterials) => {
    propellerMaterials.preload();

        new OBJLoader()
            .setMaterials(propellerMaterials)
            .load('assets/models/AW101/propeller.obj',  (propeller) => {
                propeller.position.set(0, 78, 44);

                const texture = new TextureLoader().load('/assets/models/AW101/textures/AW101.png');

                propeller.traverse( (child) => {
                    child.castShadow = true;

                    if (child instanceof Mesh) {
                        child.material.map = texture;
                    }
                });

                propellerObj = propeller;

                scene.add(propeller);
            });
    });

loader.load('assets/models/AW101/helicopter.mtl', (helicopterMaterials) => {
    helicopterMaterials.preload();

    new OBJLoader()
        .setMaterials(helicopterMaterials)
        .load('assets/models/AW101/helicopter.obj',  (helicopter) => {
            helicopter.position.set(0, 70, 0);

            const texture = new TextureLoader().load('/assets/models/AW101/textures/AW101.png');

            helicopter.traverse( (child) => {
                child.castShadow = true;

                if (child instanceof Mesh) {
                    child.material.map = texture;
                }
            });

            helicopterObj = helicopter;

            scene.add(helicopter);
        });
});

loader.load('assets/models/AW101/tail_propeller.mtl', (tailPropellerMaterials) => {
    tailPropellerMaterials.preload();

    new OBJLoader()
        .setMaterials(tailPropellerMaterials)
        .load('assets/models/AW101/tail_propeller.obj',  (tailPropeller) => {
            tailPropeller.position.set(5, 75, -65);

            const texture = new TextureLoader().load('/assets/models/AW101/textures/AW101.png');

            tailPropeller.traverse( (child) => {
                child.castShadow = true;

                if (child instanceof Mesh) {
                    child.material.map = texture;
                }
            });

            tailPropellerObj = tailPropeller;

            scene.add(tailPropeller);
        });
});

// Dev only
// const helper = new CameraHelper(directionalLight.shadow.camera);
// scene.add(helper);
// scene.add(new AxesHelper(300));

window.addEventListener('resize', onWindowResize);
animate();

function onWindowResize(): void {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
}

function animate() {
    requestAnimationFrame(animate);
    orbitControls.update();

    if (propellerObj && tailPropellerObj) {
        propellerObj.rotation.y += 0.05;
        tailPropellerObj.rotation.x += 0.05;
    }

    renderer.render(scene, camera);
}
